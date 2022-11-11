package com.promineotech.video.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.video.entity.Orders;
import com.promineotech.video.service.AddToOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultAddToOrderController implements AddToOrderController {

  @Autowired
  private AddToOrderService addToOrderService;

  @Override
  public Orders addToOrder(int order_id, BigDecimal price) {
    log.info("Controller: A request has been made to add an additional price to an order. "
        + "Parameters: , order_id = {}, price = {}", order_id, price);
    return addToOrderService.addPrice(order_id, price);
  }
}