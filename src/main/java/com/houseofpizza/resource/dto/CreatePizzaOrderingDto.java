package com.houseofpizza.resource.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
// TODO : Fix with correct dependency swagger
//@ApiModel(value = "CreatePizzaOrderingDto", description = "Rappresenta l'input per la creazione di un ordine")
public class CreatePizzaOrderingDto implements Serializable {

    private List<OrderingDto> orderingDtoList;

    public CreatePizzaOrderingDto() {
    }
}
