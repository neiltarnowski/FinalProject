package com.promineotech.video.service;

import java.util.List;
import com.promineotech.video.entity.Customer;

public interface CustomerNameService {

  List<Customer> fetchCustomerByLastName(String last_name);

}
