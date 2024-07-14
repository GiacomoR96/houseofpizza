package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.OrderController;
import com.houseofpizza.mapper.PizzaMapper;
import com.houseofpizza.mapper.PizzaToOrderMapper;
import com.houseofpizza.model.PizzaToOrder;
import com.houseofpizza.representation.StatusOrderModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class StatusOrderAssembler extends RepresentationModelAssemblerSupport<PizzaToOrder, StatusOrderModel> {

    public StatusOrderAssembler() {
        super(OrderController.class, StatusOrderModel.class);
    }

    @Override
    @NonNull
    public StatusOrderModel toModel(@NonNull PizzaToOrder entity) {
        StatusOrderModel result = PizzaToOrderMapper.INSTANCE.entityToModel(entity);
        result.setProduct(PizzaMapper.INSTANCE.entityToModel(entity.getPizza()));
        return result;
    }

}
