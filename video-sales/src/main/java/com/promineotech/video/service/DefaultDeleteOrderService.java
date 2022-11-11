package com.promineotech.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.video.dao.DeleteOrderDao;
import com.promineotech.video.entity.Orders;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultDeleteOrderService implements DeleteOrderService {

  @Autowired
  private DeleteOrderDao deleteOrderDao;

  @Override
  public Orders deleteOrder(int order_id) {
    log.info("Service has received request to delete order with order_id: {}", order_id);
    Orders order = deleteOrderDao.fetchOrder(order_id);

    return deleteOrderDao.deleteOrder(order_id, order);
  }
}
