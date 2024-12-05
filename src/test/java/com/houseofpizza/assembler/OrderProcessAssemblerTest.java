package com.houseofpizza.assembler;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.houseofpizza.representation.OrderProcessingModel;

@ExtendWith(MockitoExtension.class)
class OrderProcessAssemblerTest {

    @InjectMocks
    private OrderProcessAssembler assembler;

    @Test
    void assemblerToModelTest() {
        OrderProcessingModel resource = assembler.toModel(Long.MAX_VALUE);
        assertNotNull(resource);
        assertNotNull(resource.getOrderNumber());
    }

}
