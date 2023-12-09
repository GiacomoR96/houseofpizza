package com.houseofpizza.service;

import com.houseofpizza.bin.OrderProcessingBin;
import com.houseofpizza.error.ErrorException;

public interface OrderProcessingService {
    OrderProcessingBin getOrderProcessing() throws ErrorException, InterruptedException;
}
