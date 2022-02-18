/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.ejb.ClientManagerLocal;
import com.udea.ejb.PaymentinfoManagerLocal;
import com.udea.entity.BuyOrder;
import com.udea.entity.Client;
import com.udea.entity.Paymentinfo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author royx5
 */
@Named(value = "clientMBean")
@SessionScoped
public class ClientMBean implements Serializable {

    @EJB
    private PaymentinfoManagerLocal paymentinfoManager;

    @EJB
    private ClientManagerLocal clientManager;

    @EJB
    private com.udea.ejb.BuyOrderManagerLocal buyOrderManager;

    /**
     * Creates a new instance of ClientMBean
     */
    public ClientMBean() {
    }
    
    private Client client;
    private Paymentinfo paymentinfo;
    private BuyOrder buyOrder;
    private String cvv;
    private List<Client> clients;

    
    public Client getClientInfo(){
        System.err.println("GOOOOOD PLEASEEEEE");
        return client;
    }
    
    public Paymentinfo getPaymentinfo(){
        return paymentinfo;
    }
    
    public BuyOrder getOrder(){
        return buyOrder;
    }
    
    public void setClientInfo(Client client){
        this.client = client;
    }
    
    public void setPaymentinfo(Paymentinfo paymentinfo){
        this.paymentinfo = paymentinfo;
    }
        
    public void setBuyOrder(BuyOrder buyOrder){
        this.buyOrder = buyOrder;
    }
    
    public String pay(){
        System.out.println("PAY THIS SHET");
        return "PAY";
    }
    
    public String cancel(){
        return "CANCEL";
    }
    
    public String payAgain(){
        return "PAY_AGAIN";
    }
    
    public String getCvv(){
        return cvv;
    }
    
    public void setCvv(String cvv){
        int num;
        try{
            num = Integer.parseInt(cvv);
        }catch(NumberFormatException nfe){
            return;
        }
        if(num >= 100 && num <= 999){
            this.cvv = cvv;
        }
    }
    
        public String pay(String orderCost, String email, String username, String phoneNumber,
            String cardNumber, String expDate, String cvv, String cardOwner){
        
        Client c = new Client(username, email);
        c.setPhoneNumber(Long.parseLong(phoneNumber)); 
        client=c;
        
        Paymentinfo pi = null;
        if(checkCreditCard(cardNumber)){
            pi = new Paymentinfo(Long.parseLong(cardNumber),
                    formatExpDate(expDate), cardOwner);
            paymentinfo=pi;
        }
        
        BuyOrder bo = new BuyOrder(0000, BigDecimal.valueOf(Double.parseDouble(orderCost)));
        bo.setTimestamp(new Date());
        buyOrder = bo;
        if(checkCVV(cvv) && checkCost(orderCost)){
            completePay(c, pi, bo);
            System.out.print("YOOO");
            return "PAY";
        }
        return "PAY";
    }
    
    private void completePay(Client c, Paymentinfo pi, BuyOrder bo){
        if(!clients.contains(c)){
            clientManager.update(c);
        }
        if(!paymentinfo.equals(pi)){
            paymentinfoManager.savePayment(paymentinfo);
        }
        buyOrderManager.saveBuyOrder(bo);
    }
    
        public List<Client> getClients(){
        if((clients==null) || clients.isEmpty()){
            refresh();
        }
        return clients;
    }
        
    private void refresh(){
        clients = clientManager.getAllClients();
    }
    
    private boolean checkCreditCard(String cardNumber){
        Long number;
        try{
            number = Long.parseLong(cardNumber);
        }catch(NumberFormatException nfe){
            return false;
        }
        return cardNumber.length() >= 12;
    }
    
  
    private Date formatExpDate(String expDate){        
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/uuuu");
        LocalDate ldate = YearMonth.parse(expDate, f)
                .atDay(1);
        Date date = Date.from(ldate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
    
    private boolean checkCVV(String cvv){
        int number;
        try{
            number = Integer.parseInt(cvv);
        }catch(NumberFormatException nfe){
            return false;
        }
        return number >= 100 && number <=999;
    }
    
    private boolean checkCost(String cost){
        BigDecimal number;
        try{
            number = BigDecimal.valueOf(Double.parseDouble(cost));
        }catch(NumberFormatException nfe){
            return false;
        }
        BigDecimal low = new BigDecimal("500.00");
        BigDecimal high = new BigDecimal("10000.00");
        return (number.compareTo(low) + high.compareTo(number)) == 2;
    }
    
    public String checkCardNumber(String cardNumber){
        int starts = Integer.parseInt(cardNumber.substring(0, 5)); 
        String brand = "";
        if(starts >= 11111 && starts <= 22222) brand = "American Express";
        else if(starts >= 33334 && starts <= 44444) brand = "Diners";
        else if(starts >= 55555 && starts <= 66666) brand = "Visa";
        else if(starts >= 77777 && starts <= 88888) brand = "Mastercard";
        return brand;
    }
    
    public String getCardAlias(){
        String s = paymentinfo.getCardNumber()
                .toString();
        return "Tarjeta terminada en " + s.substring(s.length()-4, s.length());
    }
}
