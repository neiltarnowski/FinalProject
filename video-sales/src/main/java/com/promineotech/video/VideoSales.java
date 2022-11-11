package com.promineotech.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.promineotech"})
public class VideoSales {

  public static void main(String[] args) {
    SpringApplication.run(VideoSales.class, args);
  }
}
