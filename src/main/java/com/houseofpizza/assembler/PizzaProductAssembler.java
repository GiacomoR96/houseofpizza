package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.PizzaController;
import com.houseofpizza.mapper.PizzaMapper;
import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.ProductsModel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Scope("prototype")
@Component
public class PizzaProductAssembler extends RepresentationModelAssemblerSupport<Pizza, ProductsModel> {

    public PizzaProductAssembler() {
        super(PizzaController.class, ProductsModel.class);
    }

    @Override
    @NonNull
    public ProductsModel toModel(@NonNull Pizza entity) {
        return PizzaMapper.INSTANCE.entityToModel(entity);
    }

}

