package com.houseofpizza.service;

import com.houseofpizza.bin.OrderingBin;
import com.houseofpizza.error.ErrorException;

public interface OrderingService {
    OrderingBin postOrderingService(OrderingBin orderingBin) throws ErrorException;
}
