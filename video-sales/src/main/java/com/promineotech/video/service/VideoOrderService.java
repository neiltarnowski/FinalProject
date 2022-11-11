package com.promineotech.video.service;

import com.promineotech.video.entity.Orders;

public interface VideoOrderService {
	
	Orders createOrder(int video_id, int customer_id);

	boolean checkVideo(int video_id);

	

}
