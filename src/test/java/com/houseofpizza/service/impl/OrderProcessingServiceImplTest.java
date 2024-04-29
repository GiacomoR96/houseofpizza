package com.houseofpizza.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.entity.PizzaToOrder;
import com.houseofpizza.entity.Status;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;

@ExtendWith(MockitoExtension.class)
class OrderProcessingServiceImplTest {

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private OrderProcessingServiceImpl service;

    @Test
    void getStatusOrderService() throws InterruptedException {
        doReturn(getMockPizzaToOrderEntityList())
            .when(pizzaToOrderRepository)
            .findAll(any(Specification.class));

        doReturn(getMockStatusEntityList())
            .when(statusRepository)
            .findAll(any(Specification.class));

        OrderProcessingBin out = service.getOrderProcessing();
        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getOrderNumber());
        Assertions.assertEquals(1, out.getOrderNumber().size());
    }

    private PizzaToOrder getMockPizzaToOrderEntity() {
        PizzaToOrder entity = new PizzaToOrder();
        entity.setIdOrder(1);
        entity.setIdPizza(1);
        entity.setIdStatus(1);
        return entity;
    }

    private List<PizzaToOrder> getMockPizzaToOrderEntityList() {
        return Collections.singletonList(getMockPizzaToOrderEntity());
    }

    private Status getMockStatusEntity() {
        Status entity = new Status();
        entity.setId(1);
        entity.setStatus("In coda");
        return entity;
    }

    private List<Status> getMockStatusEntityList() {
        return Collections.singletonList(getMockStatusEntity());
    }

}