package com.promineotech.video.dao;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.video.entity.Orders;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultListOrderDao implements ListOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Orders> fetchOrders() {

    log.info("All orders have been requested in DAO");

    String sql = "SELECT * FROM orders";

    return jdbcTemplate.query(sql, new RowMapper<Orders>(){

      @Override
      public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Orders.builder()
            .order_id(rs.getInt("order_id"))
            .customer_id(rs.getInt("customer_id"))
            .price(new BigDecimal(rs.getInt("price")))
            .order_date(rs.getString("order_date"))
            .build();
      }
    });
  }
}