package com.houseofpizza.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;

import com.houseofpizza.assembler.OrderProcessingAssembler;
import com.houseofpizza.dto.OrderProcessingBin;
import com.houseofpizza.representation.OrderProcessingModel;
import com.houseofpizza.service.OrderProcessingService;

@ExtendWith(MockitoExtension.class)
class OrderProcessingControllerTest {

    @Mock
    private BeanFactory beanFactory;

    @Mock
    private OrderProcessingService service;

    @Mock
    private OrderProcessingAssembler assembler;

    @Mock
    private OrderProcessingModel model;

    @InjectMocks
    private OrderProcessingController controller;

    @Test
    public void getStatusMyOrder() throws InterruptedException {
        when(model.getOrderNumber()).thenReturn(Collections.singletonList(Integer.MIN_VALUE));
        when(service.getOrderProcessing()).thenReturn(OrderProcessingBin.builder().build());
        when(assembler.populateModel(any())).thenReturn(model);

        ResponseEntity<OrderProcessingModel> response = controller.getOrderProcessing();
        assertNotNull(response);
        OrderProcessingModel body = response.getBody();
        assertNotNull(body);
        assertNotNull(body.getOrderNumber());
        assertNotEquals(0, body.getOrderNumber().size());
    }

}