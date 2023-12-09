package com.houseofpizza.controller;

import com.houseofpizza.assembler.OrderingAssembler;
import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.resource.OrderingModel;
import com.houseofpizza.resource.dto.CreatePizzaOrderingDto;
import com.houseofpizza.service.OrderingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderingControllerTest {

    @Mock
    private OrderingService service;

    @Mock
    private OrderingAssembler assembler;

    @InjectMocks
    private OrderingController controller;

    @Test
    public void getStatusMyOrder() {
        when(assembler.populateModel(any())).thenReturn(new OrderingModel());
        given(service.postOrderingService(any())).willReturn(OrderingBin.builder().build());

        ResponseEntity<OrderingModel> response = controller.postOrdering(new CreatePizzaOrderingDto(), "", "");
        assertNotNull(response);
        OrderingModel body = response.getBody();
        assertNotNull(body);
        assertNull(body.getOrderNumber());
    }

}
