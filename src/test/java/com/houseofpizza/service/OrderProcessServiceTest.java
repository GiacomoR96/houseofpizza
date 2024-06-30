package com.houseofpizza.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.houseofpizza.repository.PizzaToOrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderProcessServiceTest {

    @Mock
    private PizzaToOrderRepository pizzaToOrderRepository;

    @InjectMocks
    private OrderProcessService service;

//    @Test
//    void getStatusOrderService() throws InterruptedException {
//        doReturn(getMockPizzaToOrderEntityList())
//            .when(pizzaToOrderRepository)
//            .findAll(any(Specification.class));
//
//        doReturn(getMockStatusEntityList())
//            .when(statusRepository)
//            .findAll(any(Specification.class));
//
//        List<Long> output = service.getOrderProcessing();
//        Assertions.assertNotNull(output);
//        Assertions.assertEquals(1, output.size());
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