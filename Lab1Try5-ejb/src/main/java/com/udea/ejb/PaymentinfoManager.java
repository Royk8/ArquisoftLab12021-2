/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.entity.Paymentinfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author royx5
 */
@Stateless
public class PaymentinfoManager implements PaymentinfoManagerLocal {

    @PersistenceContext(unitName = "com.udea_Lab1Try5-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    
    @Override
    public void savePayment(Paymentinfo payment) {
        em.merge(payment);
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Paymentinfo> getAllPayment() {
        Query query = em.createNamedQuery("Paymentinfo.findAll");        
        return query.getResultList();
    }
}
