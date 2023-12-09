package com.houseofpizza.controller;

import com.houseofpizza.assembler.OrderProcessingAssembler;
import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.resource.OrderProcessingModel;
import com.houseofpizza.service.OrderProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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