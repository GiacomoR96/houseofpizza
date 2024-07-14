package com.houseofpizza.representation.dto;

import java.util.List;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Data
public class OrderingDto {

    @Valid
    @NotEmpty
    private List<@Valid ProductDto> products;

    private String email;

}
