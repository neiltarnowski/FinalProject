package com.promineotech.video.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.video.entity.Video;
import com.promineotech.video.service.ListVideoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultListVideoController implements ListVideoController {

  @Autowired
  private ListVideoService listVideoService;

  @Override
  public List<Video> fetchVideoByStatus(String status) {
    log.info("Video status = {}", status);
    
        return listVideoService.fetchVideoByStatus(status);
  }
}
