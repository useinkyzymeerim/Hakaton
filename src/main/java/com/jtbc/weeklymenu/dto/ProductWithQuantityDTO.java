package com.jtbc.weeklymenu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithQuantityDTO {
    private String productName;
    private int quantity;
}
