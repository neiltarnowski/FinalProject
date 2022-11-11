package com.promineotech.video.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.video.dao.AddToOrderDao;
import com.promineotech.video.entity.Orders;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultAddToOrderService implements AddToOrderService{

  @Autowired
  private AddToOrderDao addToOrderDao;
  
  @Override
  public Orders addPrice(int order_id, BigDecimal price) {
    log.info("Service: A request has been made to add an additional price to an order. "
        + "Parameters: , order_id = {}, price = {}", order_id, price);
    log.info("Service: Fetching order. order_id = {}", order_id);
    
    Orders order = addToOrderDao.fetchOrder(order_id);
    
    return addToOrderDao.addNewPrice(order, price);
  }

}
