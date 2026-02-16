package com.tally.mapper;

import com.tally.model.Category;
import com.tally.model.Product;
import com.tally.model.Store;
import com.tally.payload.dto.ProductDto;

public class ProductMapper {

    public static ProductDto toDTO(Product product)
    {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .storeId(product.getStore()!=null? product.getStore().getId() : null)
                .categoryDto(CategoryMapper.toDTO(product.getCategory()))
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toEntity(ProductDto productDto, Store store, Category category)
    {
        return Product.builder()
                .name(productDto.getName())
                .sku(productDto.getSku())
                .store(store)
                .category(category)
                .description(productDto.getDescription())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .brand(productDto.getBrand()).build();
    }
}
