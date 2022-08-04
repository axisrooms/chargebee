package com.axisrooms.manager;

import com.axisrooms.model.Response;
import com.axisrooms.model.CustomerModel;
import com.chargebee.models.Customer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public interface customerManager {

    Response createCustomer(CustomerModel customerRequest) throws Exception;

    Customer getCustomer(String customerId);
}
