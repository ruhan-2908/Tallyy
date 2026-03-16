package com.tally.mapper;

import com.tally.model.OrderItem;
import com.tally.payload.dto.OrderItemDto;
import com.tally.payload.dto.ProductDto;

public class OrderItemMapper {
    public static OrderItemDto toDTO(OrderItem orderItem)
    {
        if(orderItem == null) return null;
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .productDto(ProductMapper.toDTO(orderItem.getProduct()))
                .build();
    }
}
