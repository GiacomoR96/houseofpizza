package com.houseofpizza.dto;

import java.io.Serializable;

import com.houseofpizza.representation.dto.CreatePizzaOrderingDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class OrderingBin implements Serializable {

    // Input
    private CreatePizzaOrderingDto dto;
    private String personName;
    private String email;

    // Output
    private Integer orderNumber;

}
