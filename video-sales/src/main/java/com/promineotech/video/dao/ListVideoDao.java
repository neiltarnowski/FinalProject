package com.promineotech.video.dao;

import java.util.List;
import com.promineotech.video.entity.Video;

public interface ListVideoDao {

  List<Video> fetchVideoByStatus(String status);

}
