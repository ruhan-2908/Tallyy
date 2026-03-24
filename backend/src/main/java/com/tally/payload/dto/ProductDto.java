package com.tally.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;


    private String name;


    private String sku;

    private String description;


    private Double mrp;


    private Double sellingPrice;

    private String brand;

    private String image;

    private Long categoryId;
    private CategoryDto categoryDto;


    private Long storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
