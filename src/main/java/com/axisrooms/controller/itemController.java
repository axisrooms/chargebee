package com.axisrooms.controller;

import com.axisrooms.model.ItemPriceModel;
import com.axisrooms.model.Response;
import com.axisrooms.model.SubscriptionModel;
import com.chargebee.models.ItemFamily;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping(value = "/v1/chargebee/items")
@Api(description = "Api to communicate with chargebee Microservice from Channel Manager")
@Slf4j
public class itemController {

    @Autowired
    private com.axisrooms.manager.itemManager itemManager;
    @CrossOrigin
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

    @PostMapping(
            path = "/createItemPrice",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createItemPrice(@Valid @RequestBody ItemPriceModel itemPriceRequest) {
        log.info("inside createItemPrices Request Received is ====>"+itemPriceRequest);
        ResponseEntity<?> responseEntity;
        try {
            Response response = itemManager.createItemPrice(itemPriceRequest);
            responseEntity = new ResponseEntity<>(itemPriceRequest, HttpStatus.OK);
        } catch (Throwable throwable) {
            log.error("Encountered exception while create subscription", throwable);
            responseEntity = new ResponseEntity<>(itemPriceRequest, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
}
