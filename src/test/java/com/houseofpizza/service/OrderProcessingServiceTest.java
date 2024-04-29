package com.houseofpizza.service;

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

import com.houseofpizza.dto.OrderProcessingBin;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.model.Status;
import com.houseofpizza.repository.PizzaToOrderRepository;
import com.houseofpizza.repository.StatusRepository;

@ExtendWith(MockitoExtension.class)
class OrderProcessingServiceTest {

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private OrderProcessingService service;

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