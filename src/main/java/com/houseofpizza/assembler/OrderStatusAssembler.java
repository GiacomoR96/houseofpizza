package com.houseofpizza.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.OrderController;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.StatusOrderModel;
import com.houseofpizza.representation.dto.PizzaOrderingModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class OrderStatusAssembler extends RepresentationModelAssemblerSupport<Map<Pizza, String>, StatusOrderModel> {

    public OrderStatusAssembler() {
        super(OrderController.class, StatusOrderModel.class);
    }

    @Override
    @NonNull
    public StatusOrderModel toModel(@NonNull Map<Pizza, String> entity) {
        StatusOrderModel statusOrderModel = new StatusOrderModel();
        statusOrderModel.setPizzaOrderingModel(mapPizzaOrderingApiList(entity));
        return statusOrderModel;
    }

    private List<PizzaOrderingModel> mapPizzaOrderingApiList(Map<Pizza, String> entityList) {
        List<PizzaOrderingModel> list = new ArrayList<>();
        if (MapUtils.isNotEmpty(entityList)) {
            entityList.forEach((k, v) -> list.add(populatePizzaOrderingApi(k, v)));
        }
        return list;
    }

    private PizzaOrderingModel populatePizzaOrderingApi(Pizza element, String status) {
        PizzaOrderingModel api = new PizzaOrderingModel();
        api.setName(element.getName());
        api.setPrice(element.getPrice());
        api.setStatus(status);
        return api;
    }

}
