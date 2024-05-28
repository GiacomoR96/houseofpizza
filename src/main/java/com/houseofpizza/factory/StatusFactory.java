package com.houseofpizza.factory;

import com.houseofpizza.model.Status;

public class StatusFactory {

    public static Status buildQueueOrder() {
        Status entity = new Status();
        entity.setStatus("In queue");
        return entity;
    }

}
