package com.promineotech.video.dao;

import java.math.BigDecimal;

import com.promineotech.video.entity.Orders;


public interface AddToOrderDao {

	Orders fetchOrder(int order_id);
	
	Orders addNewPrice(Orders order, BigDecimal price);

}
