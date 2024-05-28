package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.OrderingController;
import com.houseofpizza.representation.OrderingModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class OrderingAssembler extends RepresentationModelAssemblerSupport<Long, OrderingModel> {

    public OrderingAssembler() {
        super(OrderingController.class, OrderingModel.class);
    }

    @Override
    @NonNull
    public OrderingModel toModel(@NonNull Long entity) {
        OrderingModel model = new OrderingModel();
        model.setOrderNumber(entity);
        return model;
    }
}
