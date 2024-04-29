package com.houseofpizza.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import com.houseofpizza.dto.StatusOrderBin;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.StatusOrderModel;
import com.houseofpizza.representation.dto.PizzaOrderingModel;

@Component
public class StatusOrderAssembler {

    public StatusOrderModel populateStatusOrderModel(StatusOrderBin bin) {
        return new StatusOrderModel(bin.getOrderNumber(), mapPizzaOrderingApiList(bin.getPizzaMap()));
    }

    private List<PizzaOrderingModel> mapPizzaOrderingApiList(Map<Pizza, String> entityList) {
        List<PizzaOrderingModel> list = new ArrayList<>();
        if (MapUtils.isNotEmpty(entityList)) {
            entityList.forEach((k, v) -> {
                list.add(populatePizzaOrderingApi(k, v));
            });
        }
        return list;
    }

    private PizzaOrderingModel populatePizzaOrderingApi(Pizza element, String status) {
        PizzaOrderingModel api = new PizzaOrderingModel();
        api.setStatus(status);
        api.setPizzaName(element.getName());
        api.setPrice(element.getPrice());
        return api;
    }

}
