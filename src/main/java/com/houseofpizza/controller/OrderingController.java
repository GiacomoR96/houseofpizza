package com.houseofpizza.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.OrderingAssembler;
import com.houseofpizza.assembler.PizzaAssembler;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.OrderingModel;
import com.houseofpizza.representation.ProductsModel;
import com.houseofpizza.representation.dto.OrderingDto;
import com.houseofpizza.service.OrderingService;
import com.houseofpizza.service.PizzaService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class OrderingController {

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OrderingAssembler assembler;

    @Autowired
    private PizzaAssembler pizzaAssembler;

    @GetMapping(value = "/pizza/products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public ResponseEntity<CollectionModel<ProductsModel>> getProducts() {
        List<Pizza> output = pizzaService.findAllPizza();
        return ok(pizzaAssembler.toCollectionModel(output));
    }

    @PostMapping(value = "/pizza/order-creation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public ResponseEntity<OrderingModel> orderCreation(@RequestBody @Valid OrderingDto dto) {
        Long output = orderingService.orderCreation(dto);
        return ok(assembler.toModel(output));
    }

}
