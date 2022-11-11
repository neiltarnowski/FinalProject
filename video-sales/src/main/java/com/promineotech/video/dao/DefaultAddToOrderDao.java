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
public class DefaultAddToOrderDao implements AddToOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Orders addNewPrice(Orders order, BigDecimal price) {
    log.info("Dao: Returning Order.");

    SqlParams params = insertSql(order, price);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    return Orders.builder()
        .order_id(order.getOrder_id())
        .customer_id(order.getCustomer_id())
        .price(order.getPrice())
        .order_date(order.getOrder_date())
        .build();
  }

  private SqlParams insertSql(Orders order_id, BigDecimal price) {
    SqlParams params = new SqlParams();


    params.sql = ""
        + "UPDATE orders "
        + "SET price = price + :price "
        + "WHERE order_id = :order_id;";


    params.source.addValue("order_id", order_id.getOrder_id());
    params.source.addValue("price", price);

    return params;
  }

  @Override
  public Orders fetchOrder(int order_id) {
    log.info("DAO: Fetching order: order_id = {}", order_id);

    String sql = ""
        + "SELECT * "
        + "FROM orders "
        + "WHERE order_id = :order_id";

    Map<String, Object> params = new HashMap<>();
    params.put("order_id", order_id);


    return jdbcTemplate.query(sql, params, new OrdersResultSetExtractor());
  }

  class OrdersResultSetExtractor implements ResultSetExtractor<Orders> {
	  
    @Override
    public Orders extractData(ResultSet rs) throws SQLException {
      rs.next();
      return Orders.builder()
          .order_id(rs.getInt("order_id"))
          .customer_id(rs.getInt("customer_id"))
          .price(rs.getBigDecimal("price"))
          .order_date(rs.getString("order_date"))
          .build();
    }
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
