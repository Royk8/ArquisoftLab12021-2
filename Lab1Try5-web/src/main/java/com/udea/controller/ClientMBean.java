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
        return "PAY";
    }
}
