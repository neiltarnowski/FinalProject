package com.promineotech.video.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.video.dao.CustomerNameDao;
import com.promineotech.video.entity.Customer;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCustomerNameService implements CustomerNameService {

  @Autowired
  private CustomerNameDao customerNameDao;

  @Override
  public List<Customer> fetchCustomerByLastName(String last_name){
    log.info("The fetchCustomerByLastName method was called with argument: (last_name = {})", last_name);

    return customerNameDao.fetchCustomerByLastName(last_name);
  }
}
