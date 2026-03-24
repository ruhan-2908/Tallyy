package com.tally.mapper;

import com.tally.model.Order;
import com.tally.payload.dto.OrderDto;
import com.tally.payload.dto.OrderItemDto;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toDTO(Order order)
    {
        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .items(order.getItems().stream().map(OrderItemMapper::toDTO).collect(Collectors.toList()))
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
