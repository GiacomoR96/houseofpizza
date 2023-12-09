package com.houseofpizza.bin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class OrderProcessingBin implements Serializable {

    // Output
    private List<Integer> orderNumber;

}
