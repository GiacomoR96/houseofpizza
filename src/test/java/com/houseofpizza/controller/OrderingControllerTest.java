package com.houseofpizza.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.houseofpizza.assembler.OrderingAssembler;
import com.houseofpizza.representation.OrderingModel;
import com.houseofpizza.representation.dto.OrderingDto;
import com.houseofpizza.service.OrderingService;

@ExtendWith(MockitoExtension.class)
class OrderingControllerTest {

    @Mock
    private OrderingService service;

    @Mock
    private OrderingAssembler assembler;

    @InjectMocks
    private OrderingController controller;

    @Test
    void getStatusMyOrder() {
        when(assembler.toModel(any())).thenReturn(new OrderingModel());
        given(service.orderCreation(any())).willReturn(null);

        ResponseEntity<OrderingModel> response = controller.orderCreation(new OrderingDto());
        assertNotNull(response);
        OrderingModel body = response.getBody();
        assertNotNull(body);
        assertNull(body.getOrderNumber());
    }

}
