package com.houseofpizza.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.OrderAssembler;
import com.houseofpizza.assembler.OrderProcessAssembler;
import com.houseofpizza.assembler.StatusOrderAssembler;
import com.houseofpizza.model.Order;
import com.houseofpizza.representation.OrderProcessingModel;
import com.houseofpizza.representation.OrderingModel;
import com.houseofpizza.representation.StatusOrderModel;
import com.houseofpizza.representation.dto.OrderingDto;
import com.houseofpizza.service.OrderService;
import com.houseofpizza.service.PizzaToOrderService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/pizza/order", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PizzaToOrderService pizzaToOrderService;

    @Autowired
    private OrderAssembler orderAssembler;
    @Autowired
    private StatusOrderAssembler statusOrderAssembler;
    @Autowired
    private OrderProcessAssembler orderProcessAssembler;

    @PostMapping(value = "/creation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<OrderingModel> orderCreation(@RequestBody @Valid final OrderingDto dto) {
        Order output = orderService.createAndSaveOrder(dto);
        return ok(orderAssembler.toModel(output));
    }

    @GetMapping(value = "/status/{order}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<CollectionModel<StatusOrderModel>> getStatusOrder(
        @PathVariable(name = "order") final Long order) {
        Order result = orderService.getStatusOrder(order);
        return ok(statusOrderAssembler.toCollectionModel(result.getPizzaToOrders()));
    }

    @PostMapping(value = "/process")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
    })
    public ResponseEntity<CollectionModel<OrderProcessingModel>> orderProcess() throws InterruptedException {
        List<Long> output = pizzaToOrderService.processOrder();
        return ok(orderProcessAssembler.toCollectionModel(output));
    }

    @DeleteMapping(value = "/delete/{order}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<StatusOrderModel> deleteOrder(@PathVariable(name = "order") final Long order) {
        orderService.deleteOrder(order);
        return ResponseEntity.noContent().build();
    }

}
