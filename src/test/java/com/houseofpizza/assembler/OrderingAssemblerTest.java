package com.houseofpizza.assembler;

import com.houseofpizza.dto.OrderingBin;
import com.houseofpizza.representation.OrderingModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OrderingAssemblerTest {

    private final OrderingAssembler assembler = new OrderingAssembler();

    @Test
    public void populateStatusOrderModelTest() {
        OrderingModel resource = assembler.populateModel(mockOutput());
        assertNotNull(resource);
        assertNotNull(resource.getOrderNumber());
        assertNotEquals(Integer.MIN_VALUE, resource.getOrderNumber());
    }

    private OrderingBin mockOutput() {
        return OrderingBin.builder()
                .orderNumber(Integer.MAX_VALUE)
                .build();
    }

}