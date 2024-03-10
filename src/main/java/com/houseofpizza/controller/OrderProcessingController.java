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

@RestController
@RequestMapping(path = "/")
public class OrderProcessingController {

    @Autowired
    private OrderProcessingService service;

    @Autowired
    private OrderProcessingAssembler assembler;

    @GetMapping(value = {"/pizza/orderProcessing"}, produces = {APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "Api per avviare la produzione di pizze, una per volta")
//    @ApiResponses(value = {
//            @ApiResponse(code = HTTP_OK, message = "OK"),
//            @ApiResponse(code = HTTP_NOT_FOUND, message = "NOT FOUND")
//    })
    public ResponseEntity<OrderProcessingModel> getOrderProcessing() throws ErrorException, InterruptedException {
        OrderProcessingBin output = service.getOrderProcessing();
        return ok(assembler.populateModel(output));
    }

}