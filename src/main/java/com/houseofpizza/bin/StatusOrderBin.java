package com.houseofpizza.bin;

import com.houseofpizza.entity.PizzaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class StatusOrderBin implements Serializable {

    // Input
    private Integer orderNumber;

    // Output
    private Map<PizzaEntity, String> pizzaMap;

}
