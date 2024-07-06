package com.houseofpizza.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseofpizza.assembler.OrderAssembler;
import com.houseofpizza.assembler.OrderProcessAssembler;
import com.houseofpizza.assembler.OrderStatusAssembler;
import com.houseofpizza.exceptions.ErrorException;
import com.houseofpizza.model.Order;
import com.houseofpizza.representation.OrderingModel;
import com.houseofpizza.representation.StatusOrderModel;
import com.houseofpizza.representation.dto.OrderingDto;
import com.houseofpizza.service.OrderProcessService;
import com.houseofpizza.service.OrderService;
import com.houseofpizza.service.OrderStatusService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/pizza/order", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private OrderProcessService orderProcessService;

    @Autowired
    private OrderAssembler orderAssembler;
    @Autowired
    private OrderStatusAssembler orderStatusAssembler;
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

//    TODO : TEMPORARY API DISABLING
//    @GetMapping(value = "/status/{order}")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "OK"),
//        @ApiResponse(responseCode = "404", description = "NOT FOUND")
//    })
//    public ResponseEntity<StatusOrderModel> getOrderStatus(
//        @PathVariable(name = "order") final Long order) throws ErrorException {
//        Map<Pizza, StatusEnum> output = orderStatusService.getStatusOrderService(order);
//        return ok(orderStatusAssembler.toModel(output));
//    }

//    TODO : TEMPORARY API DISABLING
//    @PostMapping(value = "/process")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "OK"),
//        @ApiResponse(responseCode = "404", description = "NOT FOUND")
//    })
//    public ResponseEntity<CollectionModel<OrderProcessingModel>> orderProcess()
//        throws ErrorException, InterruptedException {
//        List<Long> output = orderProcessService.getOrderProcessing();
//        return ok(orderProcessAssembler.toCollectionModel(output));
//    }

    @DeleteMapping(value = "/delete/{order}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ResponseEntity<StatusOrderModel> deleteOrder(@PathVariable(name = "order") final Long order)
        throws ErrorException {

        // TODO : [WIP] Adding delete logic after updating db structure
        return null;
    }

}
