package com.promineotech.video.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.video.dao.VideoOrderDao;
import com.promineotech.video.entity.Video;
import com.promineotech.video.entity.Orders;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultVideoOrderService implements VideoOrderService {

  @Autowired
  private VideoOrderDao videoOrderDao;

  @Override
  public boolean checkVideo(int video_id) {
    return ((Video) videoOrderDao.fetchVideo(video_id)).getVideo_id() > 0;
  }

  @Override
  @Transactional
  public Orders createOrder(int video_id, int customer_id) {
    log.info("Customer customer_id = {} and video video_id = {} has been requested in service.", video_id, customer_id);

    Video video = videoOrderDao.fetchPrice(video_id);
    BigDecimal price = video.getPrice();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String order_date = LocalDateTime.now().format(formatter);


    return videoOrderDao.saveOrder(video_id, customer_id, price, order_date);
  }
}
