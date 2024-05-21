package com.houseofpizza.representation.dto;

import java.util.List;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Data
public class OrderingDto {

    @Valid
    private List<@Valid ProductDto> products;

    @NotNull
    private String personName;

}
