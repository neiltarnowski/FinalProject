package com.promineotech.video.service;

import java.util.List;
import com.promineotech.video.entity.Video;

public interface ListVideoService {

  List<Video> fetchVideoByStatus(String status);

}
