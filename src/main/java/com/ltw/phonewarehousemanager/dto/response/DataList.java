package com.ltw.phonewarehousemanager.dto.response;

import lombok.Data;

@Data
public class DataList<T> {
    private Integer total = 0;
    private T items;
}
