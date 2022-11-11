package com.promineotech.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.video.entity.Orders;
import com.promineotech.video.service.DeleteOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultDeleteOrderController implements DeleteOrderController{

  @Autowired
  private DeleteOrderService deleteOrderService;

  @Override
  public Orders deleteOrder(int order_id) {
    log.info("A request to delete an order with order_id = {} has been made", order_id);
    
    try{
      return deleteOrderService.deleteOrder(order_id);
      
    }catch(TransientDataAccessResourceException e) {
    	
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested order does not exist.");
    }
  }
}
