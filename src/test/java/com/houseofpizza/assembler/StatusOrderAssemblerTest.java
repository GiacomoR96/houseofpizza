package com.houseofpizza.assembler;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.entity.PizzaEntity;
import com.houseofpizza.resource.StatusOrderModel;
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
        assertNotNull(resource.getPizzaOrderingDto());
        assertEquals(1, resource.getPizzaOrderingDto().size());
    }

    private StatusOrderBin mockOutput() {
        return StatusOrderBin.builder()
                .orderNumber(Integer.MIN_VALUE)
                .pizzaMap(mockPizzaList())
                .build();
    }

    private Map<PizzaEntity, String> mockPizzaList() {
        Map<PizzaEntity, String> map = new HashMap<>();
        map.put(new PizzaEntity(), "1");
        return map;
    }
}