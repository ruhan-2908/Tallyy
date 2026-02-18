package com.tally.payload.dto;


import com.tally.model.Branch;
import com.tally.model.Customer;
import com.tally.model.OrderItem;
import com.tally.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;

    private BranchDto branchDto;


    private UserDto cashier;

//    private PaymentType paymentType;
    private Long customerId;

    private Customer customerDto;

    private List<OrderItemDto> items;

}
