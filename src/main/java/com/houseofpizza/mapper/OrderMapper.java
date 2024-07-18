package com.houseofpizza.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.houseofpizza.model.Order;
import com.houseofpizza.representation.OrderingModel;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(String email);

    @Mapping(ignore = true, target = "id")
    @Mapping(source = "id", target = "order")
    @Mapping(source = "createdAt", target = "date")
    OrderingModel entityToModel(Order entity);

}

