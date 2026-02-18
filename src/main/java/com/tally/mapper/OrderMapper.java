package com.tally.mapper;

import com.tally.model.Order;
import com.tally.payload.dto.OrderDto;

public class OrderMapper {
    public static OrderDto toDTO(Order order)
    {
        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .branch(order.getBranch())
                .cashier(order.getCashier())
                .paymentType(order.getPaymentType())
                .items(order.getItems())
                .build();
    }

    public static Order toEntity(OrderDto orderDto)
    {
        return Order.builder()
                .totalAmount(orderDto.getTotalAmount())
                .createdAt(orderDto.getCreatedAt())
                .branch(orderDto.getBranch())
                .cashier(orderDto.getCashier())
                .paymentType(orderDto.getPaymentType())
                .items(orderDto.getItems())
                .build();
    }
}
