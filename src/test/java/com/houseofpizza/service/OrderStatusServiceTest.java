package com.houseofpizza.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.houseofpizza.model.Pizza;
import com.houseofpizza.repository.PizzaRepository;
import com.houseofpizza.repository.PizzaToOrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderStatusServiceTest {

    private Pizza pizza;

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @Mock
    private PizzaRepository pizzaRepository;

//    @InjectMocks
//    private OrderStatusService service;

    @BeforeEach
    void setUp() {
        pizza = getMockPizzaEntity();
    }

//    @Test
//    void getStatusOrderService() {
//
//        doReturn(getMockPizzaToOrderEntityList())
//            .when(pizzaToOrderRepository)
//            .findAll(any(Specification.class));
//
//        doReturn(Collections.singletonList(pizza))
//            .when(pizzaRepository)
//            .findAll(any(Specification.class));
//
//        doReturn(getMockStatusEntityList())
//            .when(statusRepository)
//            .findAll(any(Specification.class));
//
//        Map<Pizza, StatusEnum> map = service.getStatusOrderService(100L);
//        Assertions.assertNotNull(map);
//        Assertions.assertTrue(map.containsKey(pizza));
//        Assertions.assertTrue(map.containsValue(StatusEnum.QUEUE));
//    }

//    private PizzaToOrder getMockPizzaToOrderEntity() {
//        PizzaToOrder entity = new PizzaToOrder();
//        entity.setIdOrder(1L);
//        entity.setIdPizza(1L);
//        entity.setIdStatus(1L);
//        return entity;
//    }

//    private List<PizzaToOrder> getMockPizzaToOrderEntityList() {
//        return Collections.singletonList(getMockPizzaToOrderEntity());
//    }

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

//    private List<Status> getMockStatusEntityList() {
//        return Collections.singletonList(getMockStatusEntity());
//    }

}
