package com.promineotech.video.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.video.entity.Customer;
import com.promineotech.video.service.CustomerNameService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCustomerNameController implements CustomerNameController {

  @Autowired
  private CustomerNameService customerNameService;

  @Override
  public List<Customer> fetchCustomerByLastName(String last_name) {
    log.info("Customer last_name = {}", last_name);
    
        return customerNameService.fetchCustomerByLastName(last_name);
  }
}
