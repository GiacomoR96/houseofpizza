package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.OrderController;
import com.houseofpizza.representation.OrderProcessingModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class OrderProcessAssembler extends
    RepresentationModelAssemblerSupport<Long, OrderProcessingModel> {

    public OrderProcessAssembler() {
        super(OrderController.class, OrderProcessingModel.class);
    }

    @Override
    @NonNull
    public OrderProcessingModel toModel(@NonNull Long order) {
        OrderProcessingModel model = new OrderProcessingModel();
        model.setOrderNumber(order);
        return model;
    }

}

