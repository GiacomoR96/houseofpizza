package com.houseofpizza.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.http.ResponseEntity;

import com.houseofpizza.assembler.StatusOrderAssembler;
import com.houseofpizza.representation.StatusOrderModel;
import com.houseofpizza.representation.dto.PizzaOrderingModel;
import com.houseofpizza.service.StatusOrderService;

@ExtendWith(MockitoExtension.class)
class StatusOrderControllerTest {

    @Mock
    private StatusOrderService service;

    @Mock
    private StatusOrderAssembler assembler;

    @InjectMocks
    private StatusOrderController controller;

    @Test
    void getStatusMyOrder() {
        when(assembler.toModel(any())).thenReturn(mockStatusOrderModel());
        given(service.getStatusOrderService(any())).willReturn(new HashMap<>());

        ResponseEntity<StatusOrderModel> response = controller.getStatusOrder(Long.MIN_VALUE);
        assertNotNull(response);
        StatusOrderModel body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getPizzaOrderingModel().size());
    }

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

}
