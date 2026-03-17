package com.tally.mapper;


import com.tally.model.Order;
import com.tally.model.Product;
import com.tally.model.Refund;
import com.tally.model.ShiftReport;
import com.tally.payload.dto.OrderDto;
import com.tally.payload.dto.ProductDto;
import com.tally.payload.dto.RefundDto;
import com.tally.payload.dto.ShiftReportDto;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {
    public static ShiftReportDto toDTO(ShiftReport entity)
    {
        return ShiftReportDto.builder()
                .id(entity.getId())
                .shiftEnd(entity.getShiftEnd())
                .shiftStart(entity.getShiftStart())
                .totalSales(entity.getTotalSales())
                .totalRefunds(entity.getTotalRefunds())
                .netSale(entity.getNetSale())
                .totalOrders(entity.getTotalOrders())
                .cashier(UserMapper.toDTO(entity.getCashier()))
                .cashierId(entity.getCashier().getId())
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProducts(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();
    }
    public static List<RefundDto> mapRefunds(List<Refund> refunds)
    {
        if(refunds == null || refunds.isEmpty()) return null;
        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }
    public static List<OrderDto> mapOrders(List<Order> orders)
    {
        if(orders == null || orders.isEmpty()) return null;
        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }
    public static List<ProductDto> mapProducts(List<Product> products)
    {
        if(products == null || products.isEmpty()) return null;
        return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }



}
