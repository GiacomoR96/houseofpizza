package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.PizzaController;
import com.houseofpizza.representation.OrderingModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<Long, OrderingModel> {

    public OrderAssembler() {
        super(PizzaController.class, OrderingModel.class);
    }

    @Override
    @NonNull
    public OrderingModel toModel(@NonNull Long entity) {
        OrderingModel model = new OrderingModel();
        model.setOrderNumber(entity);
        return model;
    }
}
