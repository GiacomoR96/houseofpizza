package com.houseofpizza.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.entity.Pizza;
import com.houseofpizza.entity.PizzaToOrder;
import com.houseofpizza.entity.Status;
import com.houseofpizza.jpa.PizzaRepository;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;

@ExtendWith(MockitoExtension.class)
class StatusOrderServiceImplTest {

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private StatusOrderServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getStatusOrderService() {

        doReturn(getMockPizzaToOrderEntityList())
            .when(pizzaToOrderRepository)
            .findAll(any(Specification.class));

        doReturn(getMockPizzaEntityList())
            .when(pizzaRepository)
            .findAll(any(Specification.class));

        doReturn(getMockStatusEntityList())
            .when(statusRepository)
            .findAll(any(Specification.class));

        StatusOrderBin out = service.getStatusOrderService(retrieveStatusOrderBin());
        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getOrderNumber());
        Assertions.assertEquals(100, out.getOrderNumber().intValue());
        Assertions.assertNotNull(out.getPizzaMap());

        // TODO : FIX ME
        Map<Pizza, String> map = out.getPizzaMap();
        //Assertions.assertTrue(map.containsKey(getMockPizzaEntity()));
        //Assertions.assertTrue(map.containsValue("In coda"));
    }

    private StatusOrderBin retrieveStatusOrderBin() {
        return StatusOrderBin.builder()
            .orderNumber(100)
            .build();
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

    private Pizza getMockPizzaEntity() {
        Pizza entity = new Pizza();
        entity.setId(1);
        entity.setName("Carbonara");
        entity.setPrice(6.0);
        return entity;
    }

    private List<Pizza> getMockPizzaEntityList() {
        return Collections.singletonList(getMockPizzaEntity());
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