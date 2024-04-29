package com.houseofpizza.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.houseofpizza.model.Pizza;
import com.houseofpizza.representation.ProductsModel;

@Mapper
public interface PizzaMapper {

    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    ProductsModel entityToModel(Pizza entity);

}
