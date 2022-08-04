package com.axisrooms.controller;

import com.axisrooms.manager.customerManager;
import com.axisrooms.model.Response;
import com.axisrooms.model.CustomerModel;
import com.chargebee.models.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.SocketTimeoutException;

@RestController
@RequestMapping(value = "/v1/chargebee")
@Api(description = "Api to communicate with chargebee Microservice from Channel Manager")
@Slf4j
public class customerController {

    @Value("${microservice.communication.token}")
    private String acceptedToken;

    @Autowired
    private customerManager customerManager;
    @GetMapping(
            path = "/getCustomer/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Fetch customer from chargebee given a hotelId.",
            response = Response.class
    )
    public ResponseEntity<?> getCustomer(@PathVariable("customerId") String customerId) {
        ResponseEntity<?> responseEntity;
        try {
            // Utils.isValid(token, hotelId, acceptedToken);
            Customer response = customerManager.getCustomer(customerId);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create customer", throwable);
            responseEntity = new ResponseEntity<>(new Response(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
    @PostMapping(
            path = "/createCustomer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerModel customerRequest) {
        log.info("inside update inventory api of easemytrip ota Request Received is ====>"+customerRequest);
        ResponseEntity<?> responseEntity;
        try {
            Response response = customerManager.createCustomer(customerRequest);
            customerRequest.setId(response.getMessage());
            responseEntity = new ResponseEntity<>(customerRequest, HttpStatus.OK);
        } catch (SocketTimeoutException e) {
            log.error("Encountered exception while getting rooms", e);
            responseEntity = new ResponseEntity<>(customerRequest, HttpStatus.GATEWAY_TIMEOUT);
            return responseEntity;
        }catch (Throwable throwable) {
            log.error("Encountered exception while update inventory", throwable);
            responseEntity = new ResponseEntity<>(customerRequest, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
}
