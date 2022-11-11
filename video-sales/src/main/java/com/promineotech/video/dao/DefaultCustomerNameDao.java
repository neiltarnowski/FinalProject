package com.promineotech.video.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.video.entity.Customer;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCustomerNameDao implements CustomerNameDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Customer> fetchCustomerByLastName(String last_name) {
    last_name = "%" + last_name + "%";
    log.info("DAO: last_name = {}", last_name);

    String sql = ""
        + "SELECT * "
        + "FROM customer "
        + "WHERE last_name "
        + "LIKE :last_name";

    Map<String, Object> params = new HashMap<>();
    params.put("last_name", last_name);

    return jdbcTemplate.query(sql, params,new RowMapper<Customer>() {

      @Override
      public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Customer.builder()
            .customer_id(rs.getInt("customer_id"))
            .first_name(rs.getString("first_name"))
            .last_name(rs.getString("last_name"))
            .email(rs.getString("email"))
            .phone(rs.getString("phone"))
            .build();
      }
    });
  }
}
