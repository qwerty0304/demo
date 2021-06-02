package com.example.demo.model;

import lombok.Data;

@Data
public class Discount {
    private String code;
    private Double discount;
    private String expiry;
}
