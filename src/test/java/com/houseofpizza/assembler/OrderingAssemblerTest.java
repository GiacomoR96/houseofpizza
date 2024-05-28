package com.houseofpizza.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.houseofpizza.representation.OrderingModel;

@ExtendWith(MockitoExtension.class)
class OrderingAssemblerTest {

    @InjectMocks
    private OrderingAssembler assembler;

    @Test
    void populateStatusOrderModelTest() {
        OrderingModel resource = assembler.toModel(Long.MAX_VALUE);
        assertNotNull(resource);
        assertNotNull(resource.getOrderNumber());
        assertEquals(Long.MAX_VALUE, resource.getOrderNumber());
    }

}