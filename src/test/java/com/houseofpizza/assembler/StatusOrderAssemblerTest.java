package com.houseofpizza.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.StatusOrderModel;

@ExtendWith(MockitoExtension.class)
class StatusOrderAssemblerTest {

    @InjectMocks
    private StatusOrderAssembler assembler;

    @Test
    void populateStatusOrderModelTest() {
        StatusOrderModel resource = assembler.toModel(mockPizzaMap());
        assertNotNull(resource);
        assertNotNull(resource.getPizzaOrderingModel());
        assertEquals(1, resource.getPizzaOrderingModel().size());
    }

    private Map<Pizza, String> mockPizzaMap() {
        Map<Pizza, String> map = new HashMap<>();
        map.put(new Pizza(), "In queue");
        return map;
    }

}
