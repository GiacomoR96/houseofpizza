package com.houseofpizza.bin;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class OrderProcessingBin implements Serializable {

    // Output
    private List<Integer> orderNumber;

}
