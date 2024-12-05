package com.houseofpizza.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.houseofpizza.representation.OrderingModel;

@ExtendWith(MockitoExtension.class)
class OrderAssemblerTest {

    @InjectMocks
    private OrderAssembler assembler;

//    @Test
//    void assemblerToModelTest() {
//        OrderingModel resource = assembler.toModel(Long.MAX_VALUE);
//        assertNotNull(resource);
//        assertNotNull(resource.getOrder());
//        assertEquals(Long.MAX_VALUE, resource.getOrder());
//    }

}