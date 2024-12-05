package com.houseofpizza.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatusOrderAssemblerTest {

    @InjectMocks
    private StatusOrderAssembler assembler;

//    @Test
//    void assemblerToModelTest() {
//        StatusOrderModel resource = assembler.toModel(mockPizzaMap());
//        assertNotNull(resource);
//        assertNotNull(resource.getPizzaToOrderModel());
//        assertEquals(1, resource.getPizzaToOrderModel().size());
//    }

//    private Map<Pizza, StatusEnum> mockPizzaMap() {
//        Map<Pizza, StatusEnum> map = new HashMap<>();
//        map.put(new Pizza(), StatusEnum.QUEUE);
//        return map;
//    }

}
