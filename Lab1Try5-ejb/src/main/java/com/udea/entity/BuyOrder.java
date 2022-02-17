/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author royx5
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuyOrder.findAll", query = "SELECT b FROM BuyOrder b")
    , @NamedQuery(name = "BuyOrder.findByOrderid", query = "SELECT b FROM BuyOrder b WHERE b.orderid = :orderid")
    , @NamedQuery(name = "BuyOrder.findByOrderCost", query = "SELECT b FROM BuyOrder b WHERE b.orderCost = :orderCost")
    , @NamedQuery(name = "BuyOrder.findByTimestamp", query = "SELECT b FROM BuyOrder b WHERE b.timestamp = :timestamp")})
public class BuyOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderid")
    private Integer orderid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_cost")
    private BigDecimal orderCost;
    @Column(name = "timestamp")
    @Temporal(TemporalType.DATE)
    private Date timestamp;
    @JoinColumn(name = "client_email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Client clientEmail;

    public BuyOrder() {
    }

    public BuyOrder(Integer orderid) {
        this.orderid = orderid;
    }

    public BuyOrder(Integer orderid, BigDecimal orderCost) {
        this.orderid = orderid;
        this.orderCost = orderCost;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuyOrder)) {
            return false;
        }
        BuyOrder other = (BuyOrder) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.entity.BuyOrder[ orderid=" + orderid + " ]";
    }
    
}
