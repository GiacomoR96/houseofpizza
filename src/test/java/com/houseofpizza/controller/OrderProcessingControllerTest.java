package com.houseofpizza.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.houseofpizza.assembler.OrderProcessingAssembler;
import com.houseofpizza.representation.OrderProcessingModel;
import com.houseofpizza.service.OrderProcessingService;

@ExtendWith(MockitoExtension.class)
class OrderProcessingControllerTest {

    @Mock
    private BeanFactory beanFactory;

    @Mock
    private OrderProcessingService service;

    @Mock
    private OrderProcessingAssembler assembler;

    @InjectMocks
    private OrderProcessingController controller;

    @Test
    void getStatusMyOrder() throws InterruptedException {
        when(service.getOrderProcessing()).thenReturn(Collections.singletonList(1L));
        when(assembler.toCollectionModel(any())).thenReturn(mockCollectionOrderProcessingModel());

        ResponseEntity<CollectionModel<OrderProcessingModel>> response = controller.getOrderProcessing();
        assertNotNull(response);
        CollectionModel<OrderProcessingModel> collectionModel = response.getBody();
        assertNotNull(collectionModel);
        assertNotNull(collectionModel.getContent());
        assertEquals(1, collectionModel.getContent().size());
    }

    private CollectionModel<OrderProcessingModel> mockCollectionOrderProcessingModel() {
        return CollectionModel.of(
            Collections.singletonList(mockOrderProcessingModel()));
    }

    private OrderProcessingModel mockOrderProcessingModel() {
        OrderProcessingModel orderProcessingModel = new OrderProcessingModel();
        orderProcessingModel.setOrderNumber(Long.MIN_VALUE);
        return orderProcessingModel;
    }

}
