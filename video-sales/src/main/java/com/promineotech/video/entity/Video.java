package com.promineotech.video.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Video {

	  private int video_id;
	  private String title;
	  private String status;
	  private String start_date;
	  private String deadline;
	  private BigDecimal price;
	}
