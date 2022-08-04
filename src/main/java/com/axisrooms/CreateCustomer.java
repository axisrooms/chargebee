package com.axisrooms;


import com.chargebee.*;
import com.chargebee.models.*;
import com.chargebee.models.enums.*;
import com.chargebee.exceptions.*;
import com.chargebee.models.Customer;
import com.chargebee.Environment;

public class CreateCustomer{

    /*
    creates a customer with billing address.
*/
public void createCustomer() throws Exception {
    Environment.configure("https://axisrooms-test.chargebee.com/","");
    Result result = Customer.create()
            .firstName("Ankr")
            .lastName("Jain")
            .email("ankr@test.com")
            .locale("fr-CA")
            .billingAddressFirstName("Ankr")
            .billingAddressLastName("Jain")
            .billingAddressLine1("PO Box 9999")
            .billingAddressCity("Walnut")
            .billingAddressState("GJ")
            .billingAddressZip("91789")
            .billingAddressCountry("IN")
            .request();
    Customer customer = result.customer();
    Card card = result.card();
}
}
