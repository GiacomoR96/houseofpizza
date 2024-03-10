package com.houseofpizza.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.StatusOrderAssembler;
import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.error.ErrorException;
import com.houseofpizza.factory.StatusOrderBinFactory;
import com.houseofpizza.resource.StatusOrderModel;
import com.houseofpizza.service.StatusOrderService;

@RestController
@RequestMapping(path = "/")
public class StatusOrderController {

    @Autowired
    private StatusOrderService service;

    @Autowired
    private StatusOrderAssembler statusOrderAssembler;


    @GetMapping(value = {"/pizza/statusMyOrder/{orderNumber}"}, produces = {APPLICATION_JSON_VALUE})
    // TODO : Fix with correct dependency swagger
//    @ApiOperation(value = "Api utile per controllare lo stato dell'ordine")
//    @ApiResponses(value = {
//            @ApiResponse(code = HTTP_OK, message = "OK"),
//            @ApiResponse(code = HTTP_NOT_FOUND, message = "NOT FOUND")
//    })
    public ResponseEntity<StatusOrderModel> getStatusMyOrder(
        @RequestParam(name = "orderNumber", required = true) final Integer orderNumber
    ) throws ErrorException {
        StatusOrderBin bin = StatusOrderBinFactory.create(orderNumber);
        StatusOrderBin output = service.getStatusOrderService(bin);

        return ok(statusOrderAssembler.populateStatusOrderModel(output));
    }

}