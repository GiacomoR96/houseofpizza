package com.houseofpizza.resource.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CreatePizzaOrderingDto implements Serializable {

    private List<OrderingDto> orderingDtoList;

    public CreatePizzaOrderingDto() {
    }
}
