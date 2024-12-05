package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.PizzaController;
import com.houseofpizza.mapper.OrderMapper;
import com.houseofpizza.model.Order;
import com.houseofpizza.representation.OrderingModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<Order, OrderingModel> {

    public OrderAssembler() {
        super(PizzaController.class, OrderingModel.class);
    }

    @Override
    @NonNull
    public OrderingModel toModel(@NonNull Order entity) {
        return OrderMapper.INSTANCE.entityToModel(entity);
    }

}

