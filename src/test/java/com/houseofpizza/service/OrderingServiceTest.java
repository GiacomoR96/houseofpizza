package com.houseofpizza.service;

import com.houseofpizza.dto.OrderingBin;
import com.houseofpizza.model.Order;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.model.Status;
import com.houseofpizza.repository.OrderRepository;
import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.StatusRepository;
import com.houseofpizza.representation.dto.CreatePizzaOrderingDto;
import com.houseofpizza.representation.dto.OrderingDto;

import org.junit.jupiter.api.Assertions;
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
class OrderingServiceTest {

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderingService service;

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

        when(statusRepository.save(any(Status.class))).thenReturn(getMockStatusEntity());
        when(pizzaToOrderRepository.save(any(PizzaToOrder.class))).thenReturn(getMockPizzaToOrderEntity());

        OrderingBin out = service.postOrderingService(retrieveOrderingBin());
        Assertions.assertNotNull(out);
        Assertions.assertNotNull(out.getOrderNumber());
        Assertions.assertEquals(100, out.getOrderNumber().intValue());
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

    private Order getMockOrderEntity() {
        Order entity = new Order();
        entity.setId(100);
        entity.setPersonName("");
        entity.setEmail("test@gmail.com");
        return entity;
    }

    private List<Order> getMockOrderEntityList() {
        return Collections.singletonList(getMockOrderEntity());
    }

    private PizzaToOrder getMockPizzaToOrderEntity() {
        PizzaToOrder entity = new PizzaToOrder();
        entity.setIdOrder(1);
        entity.setIdPizza(1);
        entity.setIdStatus(1);
        return entity;
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

}