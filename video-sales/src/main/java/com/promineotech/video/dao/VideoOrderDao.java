package com.promineotech.video.dao;

import java.math.BigDecimal;

import com.promineotech.video.entity.Orders;
import com.promineotech.video.entity.Video;

public interface VideoOrderDao {
	
	Orders saveOrder(int video_id, int customer_id, BigDecimal price, String order_date);

	Object fetchVideo(int video_id);

	Video fetchPrice(int video_id);

    void createNewVideoOrder(int video_id, int order_id);
}
