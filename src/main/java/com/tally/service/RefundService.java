package com.tally.service;

import com.tally.model.Refund;
import com.tally.payload.dto.RefundDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {
    RefundDto createRefund(Refund  refund) throws  Exception;
    List<RefundDto> getAllRefunds() throws  Exception;
    RefundDto getRefundByCashier(Long cashierId) throws  Exception;
    RefundDto getRefundByShiftReport(Long shiftReportId) throws Exception;
    List<RefundDto> getRefundByCashierAndDateRange(Long cashierId,
                                                   LocalDateTime startDate,
                                                   LocalDateTime endDate) throws  Exception;
    List<RefundDto> getRefundByBranch(Long branchId) throws  Exception;
    RefundDto getRefundById(Long refundId) throws  Exception;
    void deleteRefund(Long refundId) throws Exception;
}
