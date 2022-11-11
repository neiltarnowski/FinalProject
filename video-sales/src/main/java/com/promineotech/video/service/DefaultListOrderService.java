package com.promineotech.video.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.video.dao.ListOrderDao;
import com.promineotech.video.entity.Orders;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultListOrderService implements ListOrderService {

  @Autowired
  private ListOrderDao listOrderDao;

  @Override
  public List<Orders> fetchOrders(){
    log.info("All orders have been requested in Service");

    return listOrderDao.fetchOrders();
  }
}
