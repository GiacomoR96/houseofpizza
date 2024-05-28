package com.houseofpizza.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.OrderProcessingAssembler;
import com.houseofpizza.exceptions.ErrorException;
import com.houseofpizza.representation.OrderProcessingModel;
import com.houseofpizza.service.OrderProcessingService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class OrderProcessingController {

    @Autowired
    private OrderProcessingService service;

    @Autowired
    private OrderProcessingAssembler assembler;

    @GetMapping(value = "/pizza/orderProcessing")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<CollectionModel<OrderProcessingModel>> getOrderProcessing()
        throws ErrorException, InterruptedException {
        List<Long> output = service.getOrderProcessing();
        return ok(assembler.toCollectionModel(output));
    }

}