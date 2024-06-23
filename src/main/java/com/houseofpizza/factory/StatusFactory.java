package com.houseofpizza.factory;

import com.houseofpizza.enums.StatusEnum;
import com.houseofpizza.model.Status;

public class StatusFactory {

    public static Status buildQueueOrder() {
        Status entity = new Status();
        entity.setStatus(StatusEnum.QUEUE);
        return entity;
    }

}
