package com.houseofpizza.representation.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

// TODO Change this name + modify this implements
@Data
public class CreatePizzaOrderingDto implements Serializable {

    private List<OrderingDto> orderingDtoList;

    public CreatePizzaOrderingDto() {
    }
}
