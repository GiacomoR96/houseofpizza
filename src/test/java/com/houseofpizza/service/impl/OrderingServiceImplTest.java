package com.houseofpizza.service.impl;

import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.entity.OrderEntity;
import com.houseofpizza.entity.PizzaEntity;
import com.houseofpizza.entity.PizzaToOrderEntity;
import com.houseofpizza.entity.StatusEntity;
import com.houseofpizza.jpa.OrderRepository;
import com.houseofpizza.jpa.PizzaRepository;
import com.houseofpizza.jpa.PizzaToOrderRepository;
import com.houseofpizza.jpa.StatusRepository;
import com.houseofpizza.resource.dto.CreatePizzaOrderingDto;
import com.houseofpizza.resource.dto.OrderingDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderingServiceImplTest {

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderingServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getStatusOrderService() {
        doReturn(getMockPizzaEntityList())
                .when(pizzaRepository)
                .findAll(any(Specification.class));

        doReturn(getMockOrderEntityList())
                .when(orderRepository)
                .findAll(any(Specification.class));

        when(statusRepository.save(any(StatusEntity.class))).thenReturn(getMockStatusEntity());
        when(pizzaToOrderRepository.save(any(PizzaToOrderEntity.class))).thenReturn(getMockPizzaToOrderEntity());

        OrderingBin out = service.postOrderingService(retrieveOrderingBin());
        Assert.assertNotNull(out);
        Assert.assertNotNull(out.getOrderNumber());
        Assert.assertEquals(100, out.getOrderNumber().intValue());
    }

    private OrderingBin retrieveOrderingBin() {
        return OrderingBin.builder()
                .dto(getMockPizzaOrderingDto())
                .personName("")
                .email("test@test.com")
                .build();
    }

    private CreatePizzaOrderingDto getMockPizzaOrderingDto() {
        CreatePizzaOrderingDto dto = new CreatePizzaOrderingDto();
        dto.setOrderingDtoList(getMockOrderingDtoList());
        return dto;
    }

    private List<OrderingDto> getMockOrderingDtoList() {
        OrderingDto dto = new OrderingDto();
        dto.setPizzaName("Capricciosa");
        return Collections.singletonList(dto);
    }

    private OrderEntity getMockOrderEntity() {
        OrderEntity entity = new OrderEntity();
        entity.setId(100);
        entity.setPersonName("");
        entity.setEmail("test@gmail.com");
        return entity;
    }

    private List<OrderEntity> getMockOrderEntityList() {
        return Collections.singletonList(getMockOrderEntity());
    }

    private PizzaToOrderEntity getMockPizzaToOrderEntity() {
        PizzaToOrderEntity entity = new PizzaToOrderEntity();
        entity.setIdOrder(1);
        entity.setIdPizza(1);
        entity.setIdStatus(1);
        return entity;
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

}