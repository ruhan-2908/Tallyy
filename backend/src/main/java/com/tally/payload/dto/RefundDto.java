package com.tally.payload.dto;



import com.tally.domain.PaymentType;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class RefundDto {

    private Long id;

    private OrderDto orderDto;
    private Long orderId;


    private String reason;

    private Double amount;


//    private ShiftReport shiftReport;
    private Long shiftReportId;


    private String cashierName;

    private BranchDto branchDto;
    private Long branchId;

    private LocalDateTime createdAt;

    private PaymentType paymentType;
}

