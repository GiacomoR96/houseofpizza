package com.houseofpizza.service;

import com.houseofpizza.bin.StatusOrderBin;
import com.houseofpizza.error.ErrorException;

public interface StatusOrderService {
    StatusOrderBin getStatusOrderService(StatusOrderBin statusOrderBin) throws ErrorException;
}
