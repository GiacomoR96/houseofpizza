package com.houseofpizza.controller;

import com.houseofpizza.assembler.OrderingAssembler;
import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.factory.OrderingBinFactory;
import com.houseofpizza.resource.OrderingModel;
import com.houseofpizza.resource.dto.CreatePizzaOrderingDto;
import com.houseofpizza.service.OrderingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/")
public class OrderingController {

    @Autowired
    private OrderingService service;

    @Autowired
    private OrderingAssembler assembler;

    @PostMapping(value = {"/pizza/ordering"}, produces = {APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Api utile per aggiungere un ordine")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "OK"),
            @ApiResponse(code = HTTP_NOT_FOUND, message = "NOT FOUND")
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