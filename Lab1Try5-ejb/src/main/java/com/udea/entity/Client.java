/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author royx5
 */
@Entity
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findByUserName", query = "SELECT c FROM Client c WHERE c.userName = :userName")
    , @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email")
    , @NamedQuery(name = "Client.findByPhoneNumber", query = "SELECT c FROM Client c WHERE c.phoneNumber = :phoneNumber")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "user_name")
    private String userName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "clientEmail")
    private Paymentinfo paymentinfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientEmail")
    private List<BuyOrder> buyOrderList;

    public Client() {
    }

    public Client(String email) {
        this.email = email;
    }

    public Client(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Paymentinfo getPaymentinfo() {
        return paymentinfo;
    }

    public void setPaymentinfo(Paymentinfo paymentinfo) {
        this.paymentinfo = paymentinfo;
    }

    @XmlTransient
    public List<BuyOrder> getBuyOrderList() {
        return buyOrderList;
    }

    public void setBuyOrderList(List<BuyOrder> buyOrderList) {
        this.buyOrderList = buyOrderList;
    }
    
    public String getCardholderName(){
        return this.paymentinfo.getCardholderName();
    }
    
    public Long getCardNumber(){
        return this.paymentinfo.getCardNumber();
    }
    
    public Date getExpirationDate(){
        return this.paymentinfo.getExpirationDate();
    }
    
    public void setPaymentInfo(Long cardNumber){
        this.paymentinfo = new Paymentinfo(cardNumber);
    }
    
    public void setPaymentInfo(Long cardNumber, Date expirationDate, String cardHolderName){
        this.paymentinfo = new Paymentinfo(cardNumber, expirationDate, cardHolderName);
    }
    
    public BigDecimal getOrderCost(){
        return buyOrderList.get(0).getOrderCost();
    }
    
    public Date getTimestamp(){
        return buyOrderList.get(0).getTimestamp();
    }
    
    public Integer getOrderId(){
        return buyOrderList.get(0).getOrderid();
    }
    
    public void setOrderCost(BigDecimal cost){
        buyOrderList.get(0).setOrderCost(cost);
    }
    
    public void setOrderCost(Date ts){
        buyOrderList.get(0).setTimestamp(ts);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.entity.Client[ email=" + email + " ]";
    }
    
}
