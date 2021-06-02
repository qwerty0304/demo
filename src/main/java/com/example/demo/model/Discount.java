package com.example.demo.model;

import lombok.Data;

@Data
public class Discount {
    private String code;
    private Integer discount;
    private String expiry;
}
