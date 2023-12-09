package com.houseofpizza.resource.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "CreatePizzaOrderingDto", description = "Rappresenta l'input per la creazione di un ordine")
public class CreatePizzaOrderingDto implements Serializable {

    private List<OrderingDto> orderingDtoList;

    public CreatePizzaOrderingDto() {
    }
}
