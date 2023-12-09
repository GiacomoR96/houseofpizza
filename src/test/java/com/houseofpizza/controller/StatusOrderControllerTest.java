package com.houseofpizza.controller;

import com.houseofpizza.assembler.StatusOrderAssembler;
import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.resource.StatusOrderModel;
import com.houseofpizza.service.StatusOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusOrderControllerTest {

    @Mock
    private StatusOrderService service;

    @Mock
    private StatusOrderAssembler assembler;

    @InjectMocks
    private StatusOrderController controller;

    @Test
    public void getStatusMyOrder() {
        when(assembler.populateStatusOrderModel(any())).thenReturn(new StatusOrderModel(Integer.MIN_VALUE, Collections.emptyList()));
        given(service.getStatusOrderService(any())).willReturn(StatusOrderBin.builder().build());

        ResponseEntity<StatusOrderModel> response = controller.getStatusMyOrder(Integer.MIN_VALUE);
        assertNotNull(response);
        StatusOrderModel body = response.getBody();
        assertNotNull(body);
        assertEquals(Integer.MIN_VALUE, body.getOrderNumber());
        assertEquals(0, body.getPizzaOrderingDto().size());
    }

}