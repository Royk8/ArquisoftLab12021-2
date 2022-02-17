/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.entity.Paymentinfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author royx5
 */
@Local
public interface PaymentinfoManagerLocal {
    
    public void savePayment(Paymentinfo payment);

    List<Paymentinfo> getAllPayment();
}
