package com.houseofpizza.bin;

import java.io.Serializable;

import com.houseofpizza.resource.dto.CreatePizzaOrderingDto;

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
