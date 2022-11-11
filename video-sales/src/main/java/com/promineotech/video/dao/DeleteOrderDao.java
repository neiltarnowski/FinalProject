package com.promineotech.video.dao;

import com.promineotech.video.entity.Orders;

public interface DeleteOrderDao {
	
	Orders deleteOrder(int order_id, Orders order);

	Orders fetchOrder(int order_id);

	

}
