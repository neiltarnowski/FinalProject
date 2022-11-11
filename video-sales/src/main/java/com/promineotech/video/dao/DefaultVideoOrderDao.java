package com.promineotech.video.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.video.entity.Video;
import com.promineotech.video.entity.Orders;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultVideoOrderDao implements VideoOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Orders saveOrder(int video_id, int customer_id, BigDecimal price, String order_date) {
    log.info("A new order has been requested for customer customer_id = {} and video video_id = {}", video_id, customer_id );
    SqlParams params = insertSql(video_id, customer_id, price);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    int order_id = keyHolder.getKey().intValue();

    createNewVideoOrder(video_id, order_id);

    return Orders.builder()
        .order_id(order_id)
        .customer_id(customer_id)
        .price(price)
        .order_date(order_date)
        .build();
  }

  private SqlParams insertSql(int video_id, int customer_id, BigDecimal price) {
    SqlParams params = new SqlParams();

    params.sql = ""
        + "INSERT INTO orders ("
        + "customer_id, price"
        + ") VALUES ("
        + ":customer_id, :price"
        + ")";

    params.source.addValue("customer_id", customer_id);
    params.source.addValue("price",price);

    return params;
  }

  @Override
  public Video fetchPrice(int video_id) {

    String sql = ""
        + "SELECT price "
        + "FROM video "
        + "WHERE video_id = :video_id";

    Map<String, Object> params = new HashMap<>();
    params.put("video_id", video_id);

    return jdbcTemplate.query(sql, params, new PriceResultSetExtractor());
  }

  class PriceResultSetExtractor implements ResultSetExtractor<Video> {
    @Override
    public Video extractData(ResultSet rs) throws SQLException {
      rs.next();

      return Video.builder().price(new BigDecimal(rs.getInt("price"))).build();
    }
  }
  
  @Override
  public Video fetchVideo(int video_id) {
    String sql = ""
        + "SELECT * "
        + "FROM video "
        + "WHERE video_id = :video_id";

    Map<String, Object> params = new HashMap<>();
    params.put("video_id", video_id);

    return jdbcTemplate.query(sql, params, new StockResultSetExtractor());
  }

  class StockResultSetExtractor implements ResultSetExtractor<Video> {
    @Override
    public Video extractData(ResultSet rs) throws SQLException {
      rs.next();

      return Video.builder().video_id(rs.getInt("video_id")).build();
    }
  }

  @Override
  public void createNewVideoOrder(int video_id, int order_id) {
    String sql = ""
        + "INSERT INTO video_orders "
        + "(video_id, order_id) "
        + "VALUES (:video_id, :order_id)";

    Map<String, Object> params = new HashMap<>();
    params.put("video_id", video_id);
    params.put("order_id", order_id);

    jdbcTemplate.update(sql, params);
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
