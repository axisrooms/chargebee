package com.axisrooms.controller;

import com.axisrooms.manager.customerManager;
import com.axisrooms.manager.meteredBill;
import com.axisrooms.model.Response;
import com.chargebee.models.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/chargebee/metered")
@Api(description = "Api to communicate with chargebee Microservice from Channel Manager")
@Slf4j
public class meteredUsageController {
    @Value("${microservice.communication.token}")
    private String acceptedToken;

    @Autowired
    private meteredBill meteredBillManager;

    @GetMapping(
            path = "/setUsage/{customerId}/{usage}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Set usage of customer for transactional invoicing.",
            response = Response.class
    )
    public ResponseEntity<?> setUsage(@PathVariable("customerId") String customerId,@PathVariable("usage") Double usage) {
        ResponseEntity<?> responseEntity;
        try {
            Response response = meteredBillManager.setUsage(customerId,usage);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create customer", throwable);
            responseEntity = new ResponseEntity<>(new Response(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
    @GetMapping(
            path = "/closeInvoice/{invoiceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Set usage of customer for transactional invoicing.",
            response = Response.class
    )
    public ResponseEntity<?> closeInvoice(@PathVariable("invoiceId") String invoiceId) {
        ResponseEntity<?> responseEntity;
        try {
            Response response = meteredBillManager.closeInvoice(invoiceId);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create customer", throwable);
            responseEntity = new ResponseEntity<>(new Response(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }

    @PostMapping(
            path = "/createInvoice",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Set usage of customer for transactional invoicing.",
            response = Response.class
    )
    public ResponseEntity<?> createInvoice() {
        ResponseEntity<?> responseEntity;
        try {
            Response response = meteredBillManager.createInvoice();
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create customer", throwable);
            responseEntity = new ResponseEntity<>(new Response(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
}
