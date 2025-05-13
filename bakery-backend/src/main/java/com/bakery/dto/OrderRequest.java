package com.bakery.dto;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Long userId;
    private String shippingAddress;
    private Double totalAmount;
    private List<OrderItemDto> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemDto {  
        private Long productId;
        private String productName;
        private Double price;
        private Integer quantity;
    }
}


