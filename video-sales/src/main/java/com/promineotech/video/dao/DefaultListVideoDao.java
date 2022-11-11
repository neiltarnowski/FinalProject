package com.promineotech.video.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.video.entity.Video;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultListVideoDao implements ListVideoDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Video> fetchVideoByStatus(String status) {
    status = "%" + status + "%";
    log.info("DAO: status = {}", status);

    String sql = ""
        + "SELECT * "
        + "FROM video "
        + "WHERE status "
        + "LIKE :status";

    Map<String, Object> params = new HashMap<>();
    params.put("status", status);

    return jdbcTemplate.query(sql, params,new RowMapper<Video>(){

      @Override
      public Video mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Video.builder()
            .video_id(rs.getInt("video_id"))
            .title(rs.getString("title"))
            .status(rs.getString("status"))
            .start_date(rs.getString("start_date"))
            .deadline(rs.getString("deadline"))
            .price(new BigDecimal(rs.getInt("price")))
            .build();
      }
    });
  }
}
