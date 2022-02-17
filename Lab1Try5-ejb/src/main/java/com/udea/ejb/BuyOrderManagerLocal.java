/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.entity.BuyOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author royx5
 */
@Local
public interface BuyOrderManagerLocal {
    
    public void saveBuyOrder(BuyOrder buyOrder);

    List<BuyOrder> getAllOrders();    
}
