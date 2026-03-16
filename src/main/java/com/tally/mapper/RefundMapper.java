package com.tally.mapper;

import com.tally.model.Refund;
import com.tally.payload.dto.RefundDto;

public class RefundMapper {
    public static RefundDto toDTO(Refund refund)
    {
        return RefundDto.builder()
                .id(refund.getId())
                .orderId(refund.getOrder().getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch().getId())
                .shiftReportId(refund.getShiftReport().getId())
                .createdAt(refund.getCreatedAt())
                .build();
    }
}
