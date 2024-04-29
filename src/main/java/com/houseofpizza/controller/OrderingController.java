package com.houseofpizza.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.OrderingAssembler;
import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.factory.OrderingBinFactory;
import com.houseofpizza.resource.OrderingModel;
import com.houseofpizza.resource.dto.CreatePizzaOrderingDto;
import com.houseofpizza.service.OrderingService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/")
public class OrderingController {

    @Autowired
    private OrderingService service;

    @Autowired
    private OrderingAssembler assembler;

    @PostMapping(value = {"/pizza/ordering"}, produces = {APPLICATION_JSON_VALUE})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<OrderingModel> postOrdering(
        @RequestBody CreatePizzaOrderingDto dto,
        @RequestParam(name = "personName", defaultValue = "CLIENTE", required = true) final String personName,
        @RequestParam(name = "email", required = false) final String email) throws ErrorException {
        OrderingBin bin = OrderingBinFactory.create(dto, personName, email);
        OrderingBin output = service.postOrderingService(bin);

        return ok(assembler.populateModel(output));
    }

}