package com.houseofpizza.assembler;

import com.houseofpizza.dto.StatusOrderBin;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.StatusOrderModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class StatusOrderAssemblerTest {

    private StatusOrderAssembler assembler = new StatusOrderAssembler();

    @Test
    public void populateStatusOrderModelTest() {
        StatusOrderModel resource = assembler.populateStatusOrderModel(mockOutput());
        assertNotNull(resource);
        assertEquals(Integer.MIN_VALUE, resource.getOrderNumber());
        assertNotNull(resource.getPizzaOrderingModel());
        assertEquals(1, resource.getPizzaOrderingModel().size());
    }

    private StatusOrderBin mockOutput() {
        return StatusOrderBin.builder()
                .orderNumber(Integer.MIN_VALUE)
                .pizzaMap(mockPizzaList())
                .build();
    }

    private Map<Pizza, String> mockPizzaList() {
        Map<Pizza, String> map = new HashMap<>();
        map.put(new Pizza(), "1");
        return map;
    }
}