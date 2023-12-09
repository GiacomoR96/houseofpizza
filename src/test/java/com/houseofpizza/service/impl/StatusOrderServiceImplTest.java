package com.houseofpizza.service.impl;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.entity.PizzaEntity;
import com.houseofpizza.entity.PizzaToOrderEntity;
import com.houseofpizza.entity.StatusEntity;
import com.houseofpizza.jpa.PizzaRepository;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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
        Assert.assertNotNull(out);
        Assert.assertNotNull(out.getOrderNumber());
        Assert.assertEquals(100, out.getOrderNumber().intValue());
        Assert.assertNotNull(out.getPizzaMap());

        Map<PizzaEntity, String> map = out.getPizzaMap();
        Assert.assertTrue(map.containsKey(getMockPizzaEntity()));
        Assert.assertTrue(map.containsValue("In coda"));
    }

    private StatusOrderBin retrieveStatusOrderBin() {
        return StatusOrderBin.builder()
                .orderNumber(100)
                .build();
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

    private PizzaEntity getMockPizzaEntity() {
        PizzaEntity entity = new PizzaEntity();
        entity.setId(1);
        entity.setName("Carbonara");
        entity.setPrice(6.0);
        return entity;
    }

    private List<PizzaEntity> getMockPizzaEntityList() {
        return Collections.singletonList(getMockPizzaEntity());
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