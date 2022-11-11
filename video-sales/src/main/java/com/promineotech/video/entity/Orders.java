package com.promineotech.video.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Orders {


  private int order_id;
  private int customer_id;
  private BigDecimal price;
  private final String order_date;
  
}
