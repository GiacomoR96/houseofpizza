package com.houseofpizza.factory;

import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.resource.dto.CreatePizzaOrderingDto;
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
