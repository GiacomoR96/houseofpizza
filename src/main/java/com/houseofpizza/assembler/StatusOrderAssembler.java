package com.houseofpizza.assembler;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.entity.PizzaEntity;
import com.houseofpizza.resource.StatusOrderModel;
import com.houseofpizza.resource.dto.PizzaOrderingDto;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StatusOrderAssembler {

    public StatusOrderModel populateStatusOrderModel(StatusOrderBin bin) {
        return new StatusOrderModel(bin.getOrderNumber(), mapPizzaOrderingApiList(bin.getPizzaMap()));
    }

    private List<PizzaOrderingDto> mapPizzaOrderingApiList(Map<PizzaEntity, String> entityList) {
        List<PizzaOrderingDto> list = new ArrayList<>();
        if (MapUtils.isNotEmpty(entityList)) {
            entityList.forEach((k, v) -> {
                list.add(populatePizzaOrderingApi(k, v));
            });
        }
        return list;
    }

    private PizzaOrderingDto populatePizzaOrderingApi(PizzaEntity element, String status) {
        PizzaOrderingDto api = new PizzaOrderingDto();
        api.setStatus(status);
        api.setPizzaName(element.getName());
        api.setPrice(element.getPrice());
        return api;
    }

}
