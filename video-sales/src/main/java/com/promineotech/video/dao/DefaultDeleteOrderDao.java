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
import org.springframework.stereotype.Service;
import com.promineotech.video.entity.Orders;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultDeleteOrderDao implements DeleteOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Orders deleteOrder(int order_id, Orders order) {
    log.info("order with id: {}, has been requested to be deleted", order_id);
    SqlParams params = insertSql(order_id);

      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(params.sql, params.source, keyHolder);
      
    return Orders.builder()
        .order_id(order_id)
        .customer_id(order.getCustomer_id())
        .price(order.getPrice())
        .order_date(order.getOrder_date())
        .build();
  }

  private SqlParams insertSql(int order_id) {
    SqlParams params = new SqlParams();

    params.sql = ""
        + "DELETE FROM orders "
        + "WHERE order_id = :order_id;";

    params.source.addValue("order_id", order_id);

    return params;
  }

  @Override
  public Orders fetchOrder(int order_id) {
    log.info("DAO: order_id = {}", order_id);

    String sql = ""
        + "SELECT * "
        + "FROM orders "
        + "WHERE order_id = :order_id";

    Map<String, Object> params = new HashMap<>();
    params.put("order_id", order_id);


    return jdbcTemplate.query(sql, params, new StockResultSetExtractor());
  }

  class StockResultSetExtractor implements ResultSetExtractor<Orders> {
    @Override
    public Orders extractData(ResultSet rs) throws SQLException {
      rs.next();
      return Orders.builder()
          .order_id(rs.getInt("order_id"))
          .customer_id(rs.getInt("customer_id"))
          .price(new BigDecimal(rs.getInt("price")))
          .order_date(rs.getString("order_date"))
          .build();
    }
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
