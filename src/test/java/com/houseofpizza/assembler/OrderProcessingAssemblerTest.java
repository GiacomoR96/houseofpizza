package com.houseofpizza.assembler;

import com.houseofpizza.dto.OrderProcessingBin;
import com.houseofpizza.representation.OrderProcessingModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OrderProcessingAssemblerTest {

    private OrderProcessingAssembler assembler = new OrderProcessingAssembler();

    @Test
    public void populateStatusOrderModelTest() {
        OrderProcessingModel resource = assembler.populateModel(mockOutput());
        assertNotNull(resource);
        assertNotNull(resource.getOrderNumber());
        assertEquals(2, resource.getOrderNumber().size());
    }

    private OrderProcessingBin mockOutput() {
        return OrderProcessingBin.builder()
                .orderNumber(Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE))
                .build();
    }

}