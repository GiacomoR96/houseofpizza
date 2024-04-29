package com.houseofpizza.factory;

import com.houseofpizza.dto.OrderingBin;
import com.houseofpizza.representation.dto.CreatePizzaOrderingDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderingBinFactory {

    public OrderingBin create(CreatePizzaOrderingDto dto, String personName, String email) {
        return OrderingBin.builder()
            .dto(dto)
            .personName(personName)
            .email(email)
            .build();
    }

}
