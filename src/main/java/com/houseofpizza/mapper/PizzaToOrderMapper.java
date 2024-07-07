package com.houseofpizza.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.houseofpizza.enums.StatusEnum;
import com.houseofpizza.model.PizzaToOrder;

@Mapper
public interface PizzaToOrderMapper {

    PizzaToOrderMapper INSTANCE = Mappers.getMapper(PizzaToOrderMapper.class);

    @Mapping(ignore = true, target = "order")
    @Mapping(ignore = true, target = "pizza")
    PizzaToOrder toEntity(StatusEnum status);

}
