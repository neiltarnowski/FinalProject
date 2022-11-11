package com.promineotech.video.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Video_orders {

	  private int video_orders_id;
	  private int video_id;
	  private String order_id;
	}
