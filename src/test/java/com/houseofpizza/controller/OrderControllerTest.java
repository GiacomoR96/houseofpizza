package com.houseofpizza.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.houseofpizza.assembler.OrderProcessAssembler;
import com.houseofpizza.assembler.OrderAssembler;
import com.houseofpizza.assembler.OrderStatusAssembler;
import com.houseofpizza.representation.OrderProcessingModel;
import com.houseofpizza.representation.OrderingModel;
import com.houseofpizza.representation.StatusOrderModel;
import com.houseofpizza.representation.dto.OrderingDto;
import com.houseofpizza.representation.dto.PizzaOrderingModel;
import com.houseofpizza.service.OrderProcessService;
import com.houseofpizza.service.OrderService;
import com.houseofpizza.service.OrderStatusService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderStatusService orderStatusService;
    @Mock
    private OrderProcessService orderProcessService;
    @Mock
    private OrderService orderService;

    @Mock
    private OrderStatusAssembler orderStatusAssembler;
    @Mock
    private OrderProcessAssembler orderProcessAssembler;
    @Mock
    private OrderAssembler orderAssembler;

    @InjectMocks
    private OrderController controller;

//    @Test
//    void getStatusMyOrder() {
//        when(orderAssembler.toModel(any())).thenReturn(new OrderingModel());
//        given(orderService.orderCreation(any())).willReturn(null);
//
//        ResponseEntity<OrderingModel> response = controller.orderCreation(new OrderingDto());
//        assertNotNull(response);
//        OrderingModel body = response.getBody();
//        assertNotNull(body);
//        assertNull(body.getOrderNumber());
//    }

//    @Test
//    void getOrderStatusTest() {
//        when(orderStatusAssembler.toModel(any())).thenReturn(mockStatusOrderModel());
//        given(orderStatusService.getStatusOrderService(any())).willReturn(new HashMap<>());
//
//        ResponseEntity<StatusOrderModel> response = controller.getOrderStatus(Long.MIN_VALUE);
//        assertNotNull(response);
//        StatusOrderModel body = response.getBody();
//        assertNotNull(body);
//        assertEquals(1, body.getPizzaOrderingModel().size());
//    }

//    @Test
//    void orderProcessTest() throws InterruptedException {
//        when(orderProcessService.getOrderProcessing()).thenReturn(Collections.singletonList(1L));
//        when(orderProcessAssembler.toCollectionModel(any())).thenReturn(mockCollectionOrderProcessingModel());
//
//        ResponseEntity<CollectionModel<OrderProcessingModel>> response = controller.orderProcess();
//        assertNotNull(response);
//        CollectionModel<OrderProcessingModel> collectionModel = response.getBody();
//        assertNotNull(collectionModel);
//        assertNotNull(collectionModel.getContent());
//        assertEquals(1, collectionModel.getContent().size());
//    }

    private StatusOrderModel mockStatusOrderModel() {
        StatusOrderModel model = new StatusOrderModel();
        model.setPizzaOrderingModel(Collections.singletonList(mockPizzaOrderingModel()));
        return model;
    }

    private PizzaOrderingModel mockPizzaOrderingModel() {
        PizzaOrderingModel model = new PizzaOrderingModel();
        model.setName("Carbonara");
        model.setPrice(6.0);
        model.setStatus("In queue");
        return model;
    }

    private CollectionModel<OrderProcessingModel> mockCollectionOrderProcessingModel() {
        return CollectionModel.of(
            Collections.singletonList(mockOrderProcessingModel()));
    }

    private OrderProcessingModel mockOrderProcessingModel() {
        OrderProcessingModel orderProcessingModel = new OrderProcessingModel();
        orderProcessingModel.setOrderNumber(Long.MIN_VALUE);
        return orderProcessingModel;
    }

}
