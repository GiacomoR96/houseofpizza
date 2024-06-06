package com.houseofpizza.service;

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

import com.houseofpizza.model.Pizza;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.model.Status;
import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.StatusRepository;

@ExtendWith(MockitoExtension.class)
class OrderStatusServiceTest {

    private Pizza pizza;

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private OrderStatusService service;

    @BeforeEach
    void setUp() {
        pizza = getMockPizzaEntity();
    }

    @Test
    void getStatusOrderService() {

        doReturn(getMockPizzaToOrderEntityList())
            .when(pizzaToOrderRepository)
            .findAll(any(Specification.class));

        doReturn(Collections.singletonList(pizza))
            .when(pizzaRepository)
            .findAll(any(Specification.class));

        doReturn(getMockStatusEntityList())
            .when(statusRepository)
            .findAll(any(Specification.class));

        Map<Pizza, String> map = service.getStatusOrderService(100L);
        Assertions.assertNotNull(map);
        Assertions.assertTrue(map.containsKey(pizza));
        Assertions.assertTrue(map.containsValue("In queue"));
    }

    private PizzaToOrder getMockPizzaToOrderEntity() {
        PizzaToOrder entity = new PizzaToOrder();
        entity.setIdOrder(1L);
        entity.setIdPizza(1L);
        entity.setIdStatus(1L);
        return entity;
    }

    private List<PizzaToOrder> getMockPizzaToOrderEntityList() {
        return Collections.singletonList(getMockPizzaToOrderEntity());
    }

    private Pizza getMockPizzaEntity() {
        Pizza entity = new Pizza();
        entity.setId(1L);
        entity.setName("Carbonara");
        entity.setPrice(6.0);
        return entity;
    }

    private Status getMockStatusEntity() {
        Status entity = new Status();
        entity.setId(1L);
        entity.setStatus("In queue");
        return entity;
    }

    private List<Status> getMockStatusEntityList() {
        return Collections.singletonList(getMockStatusEntity());
    }

}
