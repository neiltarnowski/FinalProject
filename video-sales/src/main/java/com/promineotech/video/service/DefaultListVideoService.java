package com.promineotech.video.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.video.dao.ListVideoDao;
import com.promineotech.video.entity.Video;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultListVideoService implements ListVideoService {

  @Autowired
  private ListVideoDao listVideoDao;

  @Override
  public List<Video> fetchVideoByStatus(String status){
    log.info("The fetchVideoByStatus method was called with argument: (status = {})", status);

    return listVideoDao.fetchVideoByStatus(status);
  }
}
