package com.axisrooms.manager;

import com.axisrooms.model.CustomerModel;
import com.axisrooms.model.Response;
import com.chargebee.Environment;
import com.chargebee.models.Customer;
import com.chargebee.Result;
import com.chargebee.models.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class customerManagerImpl implements customerManager {

    @Value("${microservice.communication.token}")
    private String acceptedToken;

    @Value("${siteName}")
    private String siteName;

    @Override
    public Response createCustomer(CustomerModel customerRequest) throws Exception {
        try {
            Environment.configure(siteName,acceptedToken);
            Result result = Customer.create()
                    .firstName(customerRequest.getFirst_name())
                    .lastName(customerRequest.getLast_name())
                    .email(customerRequest.getEmail())
//                    .locale("fr-CA")
//                    .billingAddressFirstName("Ankr")
//                    .billingAddressLastName("Jain")
//                    .billingAddressLine1("PO Box 9999")
//                    .billingAddressCity("Walnut")
//                    .billingAddressState("GJ")
//                    .billingAddressZip("560072")
//                    .billingAddressCountry("IN")
                    .request();
            Customer customer = result.customer();
            Card card = result.card();
            Response response = new Response();
            response.setMessage(customer.id());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomer(String customerId) {
        try {
            Environment.configure(siteName,acceptedToken);
            Result result = Customer.retrieve("__test__KyVnHhSBWlF0Q2dg").request();
            Customer customer = result.customer();
            Card card = result.card();
            Response response = new Response();
            response.setMessage("");
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
