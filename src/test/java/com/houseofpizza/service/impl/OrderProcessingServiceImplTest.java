package com.houseofpizza.service.impl;

import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.entity.PizzaToOrderEntity;
import com.houseofpizza.entity.StatusEntity;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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
        Assert.assertNotNull(out);
        Assert.assertNotNull(out.getOrderNumber());
        Assert.assertEquals(1, out.getOrderNumber().size());
    }


    private PizzaToOrderEntity getMockPizzaToOrderEntity() {
        PizzaToOrderEntity entity = new PizzaToOrderEntity();
        entity.setIdOrder(1);
        entity.setIdPizza(1);
        entity.setIdStatus(1);
        return entity;
    }

    private List<PizzaToOrderEntity> getMockPizzaToOrderEntityList() {
        return Collections.singletonList(getMockPizzaToOrderEntity());
    }

    private StatusEntity getMockStatusEntity() {
        StatusEntity entity = new StatusEntity();
        entity.setId(1);
        entity.setStatus("In coda");
        return entity;
    }

    private List<StatusEntity> getMockStatusEntityList() {
        return Collections.singletonList(getMockStatusEntity());
    }

}