package com.promineotech.video.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.video.entity.Orders;
import com.promineotech.video.service.ListOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultListOrderController implements ListOrderController {

  @Autowired
  private ListOrderService listOrderService;

  @Override
  public List<Orders> fetchOrders() {
    log.info("All orders have been requested in controller");
        return listOrderService.fetchOrders();
  }
}
