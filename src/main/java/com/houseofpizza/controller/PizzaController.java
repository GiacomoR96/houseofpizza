package com.houseofpizza.controller;

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

import com.houseofpizza.assembler.PizzaProductAssembler;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.ProductsModel;
import com.houseofpizza.service.PizzaService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/pizza", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaProductAssembler pizzaProductAssembler;

    @GetMapping(value = "/products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<CollectionModel<ProductsModel>> getProducts() {
        List<Pizza> result = pizzaService.findAll();
        return ok(pizzaProductAssembler.toCollectionModel(result));
    }

}

