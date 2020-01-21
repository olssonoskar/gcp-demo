package com.olsson.gcpdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private long userId;
    private List<ProductDto> cart;

}
