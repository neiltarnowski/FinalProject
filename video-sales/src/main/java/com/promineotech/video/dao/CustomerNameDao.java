package com.promineotech.video.dao;

import java.util.List;
import com.promineotech.video.entity.Customer;

public interface CustomerNameDao {

  List<Customer> fetchCustomerByLastName(String last_name);

}
