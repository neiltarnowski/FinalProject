package com.promineotech.video.dao;

import java.util.List;

import com.promineotech.video.entity.Orders;

public interface ListOrderDao {

	List<Orders> fetchOrders();

}
