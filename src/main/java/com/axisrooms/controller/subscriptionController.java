package com.axisrooms.controller;

import com.axisrooms.manager.subscriptionManager;
import com.axisrooms.model.SubscriptionModel;
import com.axisrooms.model.Response;
import com.chargebee.models.Subscription;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.SocketTimeoutException;

@Slf4j
public class subscriptionController {

    @Value("${microservice.communication.token}")
    private String acceptedToken;

    @Autowired
    private com.axisrooms.manager.subscriptionManager subscriptionManager;

    @GetMapping(
            path = "/getSubscription/{subscriptionId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Fetch subscription from chargebee given a hotelId.",
            response = Response.class
    )
    public ResponseEntity<?> getSubscription(@PathVariable("subscriptionId") String subscriptionId) {
        ResponseEntity<?> responseEntity;
        try {
           // Utils.isValid(token, hotelId, acceptedToken);
            Subscription response = subscriptionManager.getSubscription(subscriptionId);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create subscription", throwable);
            responseEntity = new ResponseEntity<>(new Response(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
    @PostMapping(
            path = "/createSubscription",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionModel subscriptionRequest) {
        log.info("inside update inventory api of easemytrip ota Request Received is ====>"+subscriptionRequest);
        ResponseEntity<?> responseEntity;
        try {
            Response response = subscriptionManager.createSubscription(subscriptionRequest);
            responseEntity = new ResponseEntity<>(subscriptionRequest, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create subscription", throwable);
            responseEntity = new ResponseEntity<>(subscriptionRequest, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
}
