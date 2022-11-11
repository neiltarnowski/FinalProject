package com.promineotech.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.video.entity.Orders;
import com.promineotech.video.service.VideoOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultVideoOrderController implements VideoOrderController {

  @Autowired
  private VideoOrderService videoOrderService;

  @Override
  public Orders createOrder(int video_id, int customer_id) {

    log.info("An order request has been made for video: video_id = {} and customer_id = {}.", video_id, customer_id);
    log.info("Checking to see if video_id: {} exists", video_id);

    try {
    	
      if(videoOrderService.checkVideo(video_id)) {
        log.info("video_id: {} exists! creating order...", video_id);

        return videoOrderService.createOrder(video_id, customer_id);
      }else {
        log.info("video_id: {} does not exist.", video_id);

         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The video does not exist, try again later!");
      }
    } catch (TransientDataAccessResourceException e) {
    	
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Either the supplied video_id or the customer_id is incorrect.");
    }
  }
}