package com.tally.payload.dto;


import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;

    private Integer quantity;

    private Double price;

    private ProductDto productDto;

    private Long orderId;
}
