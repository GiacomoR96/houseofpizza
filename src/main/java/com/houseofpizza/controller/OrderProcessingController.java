package com.houseofpizza.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.OrderProcessingAssembler;
import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.resource.OrderProcessingModel;
import com.houseofpizza.service.OrderProcessingService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/")
public class OrderProcessingController {

    @Autowired
    private OrderProcessingService service;

    @Autowired
    private OrderProcessingAssembler assembler;

    @GetMapping(value = {"/pizza/orderProcessing"}, produces = {APPLICATION_JSON_VALUE})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<OrderProcessingModel> getOrderProcessing() throws ErrorException, InterruptedException {
        OrderProcessingBin output = service.getOrderProcessing();
        return ok(assembler.populateModel(output));
    }

}