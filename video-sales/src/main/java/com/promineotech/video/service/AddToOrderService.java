package com.promineotech.video.service;

import java.math.BigDecimal;

import com.promineotech.video.entity.Orders;

public interface AddToOrderService {

	Orders addPrice(int order_id, BigDecimal price);
}
