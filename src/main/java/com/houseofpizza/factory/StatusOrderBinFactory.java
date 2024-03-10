package com.houseofpizza.factory;

import com.houseofpizza.bin.StatusOrderBin;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatusOrderBinFactory {

    public StatusOrderBin create(Integer orderNumber) {
        return StatusOrderBin.builder()
            .orderNumber(orderNumber)
            .build();
    }

}
