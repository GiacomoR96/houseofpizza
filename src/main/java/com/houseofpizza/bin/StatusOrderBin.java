package com.houseofpizza.bin;

import java.io.Serializable;
import java.util.Map;

import com.houseofpizza.entity.Pizza;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class StatusOrderBin implements Serializable {

    // Input
    private Integer orderNumber;

    // Output
    private Map<Pizza, String> pizzaMap;

}
