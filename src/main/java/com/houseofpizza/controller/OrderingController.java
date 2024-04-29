package com.houseofpizza.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.OrderingAssembler;
import com.houseofpizza.assembler.PizzaAssembler;
import com.houseofpizza.dto.OrderingBin;
import com.houseofpizza.exceptions.ErrorException;
import com.houseofpizza.factory.OrderingBinFactory;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.OrderingModel;
import com.houseofpizza.representation.ProductsModel;
import com.houseofpizza.representation.dto.CreatePizzaOrderingDto;
import com.houseofpizza.service.OrderingService;
import com.houseofpizza.service.PizzaService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/")
public class OrderingController {

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OrderingAssembler assembler;

    @Autowired
    private PizzaAssembler pizzaAssembler;

    @GetMapping(value = "/pizza/products", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public ResponseEntity<List<ProductsModel>> getProducts() {
        List<Pizza> output = pizzaService.retrieveAllPizza();
        return ok(pizzaAssembler.instantiateModel(output));
    }

    @PostMapping(value = "/pizza/ordering", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public ResponseEntity<OrderingModel> postOrdering(@RequestBody CreatePizzaOrderingDto dto,
                                                      @RequestParam(name = "personName", defaultValue = "CLIENTE", required = true)
                                                      final String personName,
                                                      @RequestParam(name = "email", required = false)
                                                      final String email) throws ErrorException {

        OrderingBin bin = OrderingBinFactory.create(dto, personName, email);
        OrderingBin output = orderingService.postOrderingService(bin);
        return ok(assembler.populateModel(output));
    }

}
