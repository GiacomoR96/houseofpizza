package com.houseofpizza.service;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.houseofpizza.model.Order;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.repository.OrderRepository;
import com.houseofpizza.representation.dto.OrderingDto;
import com.houseofpizza.representation.dto.ProductDto;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private PizzaToOrderService pizzaToOrderService;

    @Mock
    private PizzaService pizzaService;

    @Mock
    private OrderStatusService orderStatusService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService service;

//    @Test
//    void getStatusOrderService() {
//        doReturn(getMockOrderEntityList())
//            .when(orderRepository)
//            .findAll(any(Specification.class));
//
//        when(pizzaService.findPizzaByidPizza(anyLong())).thenReturn(getMockPizzaEntity());
//        when(orderStatusService.saveBaseStatusOrder()).thenReturn(getMockStatusEntity());
//
//
//        Long result = service.orderCreation(getMockPizzaOrderingDto());
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(100L, result);
//    }

    private OrderingDto getMockPizzaOrderingDto() {
        OrderingDto dto = new OrderingDto();
        dto.setProducts(getMockProductDto());
        dto.setEmail("Test");
        return dto;
    }

    private List<ProductDto> getMockProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(3L);
        dto.setQuantity(2L);
        return Collections.singletonList(dto);
    }

    private Order getMockOrderEntity() {
        Order entity = new Order();
        entity.setId(100L);
        entity.setPersonName("");
        return entity;
    }

    private List<Order> getMockOrderEntityList() {
        return Collections.singletonList(getMockOrderEntity());
    }

    private Pizza getMockPizzaEntity() {
        Pizza entity = new Pizza();
        entity.setId(1L);
        entity.setName("Carbonara");
        entity.setPrice(6.0);
        return entity;
    }

//    private Status getMockStatusEntity() {
//        Status entity = new Status();
//        entity.setId(1L);
//        entity.setStatus(StatusEnum.QUEUE);
//        return entity;
//    }

}