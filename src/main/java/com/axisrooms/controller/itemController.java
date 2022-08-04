package com.axisrooms.controller;

import com.axisrooms.model.Response;
import com.chargebee.models.ItemFamily;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "/v1/chargebee/items")
@Api(description = "Api to communicate with chargebee Microservice from Channel Manager")
@Slf4j
public class itemController {

    @Autowired
    private com.axisrooms.manager.itemManager itemManager;

    @GetMapping(
            path = "/getItemFamily",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Fetch customer from chargebee given a hotelId.",
            response = Response.class
    )
    public List<?> getItemFamily() {
        ResponseEntity<?> responseEntity;
        List<?> response = null;
        try {
            // Utils.isValid(token, hotelId, acceptedToken);
            response = itemManager.listItemFamily();
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create customer", throwable);
            responseEntity = new ResponseEntity<>(new Response(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return response;
    }

    @GetMapping(
            path = "/getItems",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Fetch plans from chargebee.",
            response = Response.class
    )
    public List<?> getItems() {
        ResponseEntity<?> responseEntity;
        List<?> response = null;
        try {
            // Utils.isValid(token, hotelId, acceptedToken);
            response = itemManager.listItems();
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create customer", throwable);
            responseEntity = new ResponseEntity<>(new Response(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return response;
    }
}
