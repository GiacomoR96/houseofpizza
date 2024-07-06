package com.houseofpizza.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.houseofpizza.model.Order;
import com.houseofpizza.representation.OrderingModel;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(String personName);

    @Mapping(source = "id", target = "order")
    OrderingModel entityToModel(Order entity);

}
