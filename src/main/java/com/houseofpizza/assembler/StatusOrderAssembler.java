package com.houseofpizza.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.entity.Pizza;
import com.houseofpizza.resource.StatusOrderModel;
import com.houseofpizza.resource.dto.PizzaOrderingDto;

@Component
public class StatusOrderAssembler {

    public StatusOrderModel populateStatusOrderModel(StatusOrderBin bin) {
        return new StatusOrderModel(bin.getOrderNumber(), mapPizzaOrderingApiList(bin.getPizzaMap()));
    }

    private List<PizzaOrderingDto> mapPizzaOrderingApiList(Map<Pizza, String> entityList) {
        List<PizzaOrderingDto> list = new ArrayList<>();
        if (MapUtils.isNotEmpty(entityList)) {
            entityList.forEach((k, v) -> {
                list.add(populatePizzaOrderingApi(k, v));
            });
        }
        return list;
    }

    private PizzaOrderingDto populatePizzaOrderingApi(Pizza element, String status) {
        PizzaOrderingDto api = new PizzaOrderingDto();
        api.setStatus(status);
        api.setPizzaName(element.getName());
        api.setPrice(element.getPrice());
        return api;
    }

}
