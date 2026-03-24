package com.tally.service.impl;

import com.tally.mapper.RefundMapper;
import com.tally.model.Branch;
import com.tally.model.Order;
import com.tally.model.Refund;
import com.tally.model.User;
import com.tally.payload.dto.RefundDto;
import com.tally.repository.OrderRepository;
import com.tally.repository.RefundRepository;
import com.tally.service.RefundService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final RefundRepository refundRepository;

    @Override
    public RefundDto createRefund(RefundDto refundDto) throws Exception {
        User cashier = userService.getCurrentUser();
        Order order = orderRepository.findById(refundDto.getOrderId()).orElseThrow(
                () -> new Exception("Order not found!")
        );

        Branch branch = cashier.getBranch();

        Refund createdRefund = Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refundDto.getReason())
                .amount(refundDto.getAmount())
                .createdAt(refundDto.getCreatedAt())
                .build();

        Refund savedRefund = refundRepository.save(createdRefund);
        return RefundMapper.toDTO(savedRefund);
    }

    @Override
    public List<RefundDto> getAllRefunds() throws Exception {
        return refundRepository.findAll().stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByCashier(Long cashierId) throws Exception {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByShiftReport(Long shiftReportId) throws Exception {
        return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {

        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId,startDate,endDate).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchId) throws Exception {
        return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RefundDto getRefundById(Long refundId) throws Exception {
        return RefundMapper.toDTO(refundRepository.findById(refundId).orElseThrow(
                () -> new Exception("Refund not found!")
        ));
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
        this.getRefundById(refundId);
        refundRepository.deleteById(refundId);
    }
}
