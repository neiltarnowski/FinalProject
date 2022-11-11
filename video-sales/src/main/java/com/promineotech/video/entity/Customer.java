package com.promineotech.video.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

  private int customer_id;
  private String first_name;
  private String last_name;
  private String email;
  private String phone;
}