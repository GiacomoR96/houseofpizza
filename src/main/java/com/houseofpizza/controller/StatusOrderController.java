package com.houseofpizza.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.StatusOrderAssembler;
import com.houseofpizza.exceptions.ErrorException;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.StatusOrderModel;
import com.houseofpizza.service.StatusOrderService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class StatusOrderController {

    @Autowired
    private StatusOrderService service;

    @Autowired
    private StatusOrderAssembler statusOrderAssembler;

    @GetMapping(value = "/pizza/status-order/{orderNumber}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<StatusOrderModel> getStatusOrder(
        @PathVariable(name = "orderNumber") final Long orderNumber) throws ErrorException {
        Map<Pizza, String> output = service.getStatusOrderService(orderNumber);

        return ok(statusOrderAssembler.toModel(output));
    }

}
