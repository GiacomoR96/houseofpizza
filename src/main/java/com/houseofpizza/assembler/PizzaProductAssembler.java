package com.houseofpizza.assembler;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.houseofpizza.controller.PizzaController;
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
// TODO : Resolve this problem on mapper
// return PizzaMapper.INSTANCE.entityToModel(entity);
        ProductsModel productsModel = new ProductsModel();

        productsModel.setId(entity.getId());
        productsModel.setName(entity.getName());
        productsModel.setPrice(entity.getPrice());
        productsModel.setImage(entity.getImage());

        return productsModel;
    }

}