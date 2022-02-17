/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author royx5
 */
@Entity
@Table(name = "paymentinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymentinfo.findAll", query = "SELECT p FROM Paymentinfo p")
    , @NamedQuery(name = "Paymentinfo.findByCardNumber", query = "SELECT p FROM Paymentinfo p WHERE p.cardNumber = :cardNumber")
    , @NamedQuery(name = "Paymentinfo.findByExpirationDate", query = "SELECT p FROM Paymentinfo p WHERE p.expirationDate = :expirationDate")
    , @NamedQuery(name = "Paymentinfo.findByCardholderName", query = "SELECT p FROM Paymentinfo p WHERE p.cardholderName = :cardholderName")})
public class Paymentinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_number")
    private Long cardNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cardholder_name")
    private String cardholderName;
    @JoinColumn(name = "client_email", referencedColumnName = "email")
    @OneToOne(optional = false)
    private Client clientEmail;

    public Paymentinfo() {
    }

    public Paymentinfo(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Paymentinfo(Long cardNumber, Date expirationDate, String cardholderName) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardholderName = cardholderName;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public Client getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(Client clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardNumber != null ? cardNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paymentinfo)) {
            return false;
        }
        Paymentinfo other = (Paymentinfo) object;
        if ((this.cardNumber == null && other.cardNumber != null) || (this.cardNumber != null && !this.cardNumber.equals(other.cardNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.entity.Paymentinfo[ cardNumber=" + cardNumber + " ]";
    }
    
}
