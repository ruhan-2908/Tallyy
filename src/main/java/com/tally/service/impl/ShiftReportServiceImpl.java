package com.tally.service.impl;

import com.tally.mapper.ShiftReportMapper;
import com.tally.model.*;
import com.tally.payload.dto.ShiftReportDto;
import com.tally.repository.BranchRepository;
import com.tally.repository.OrderRepository;
import com.tally.repository.RefundRepository;
import com.tally.repository.ShiftReportRepository;
import com.tally.service.ShiftReportService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;

    @Override
    public ShiftReportDto startShift(Long cashierId,
                                     Long branchId,
                                     LocalDateTime shiftStart) throws Exception {
        User currentUser = userService.getCurrentUser();
        shiftStart = LocalDateTime.now();

        LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = shiftStart.withHour(23).withMinute(59).withSecond(59);
        Optional<ShiftReport> existing = shiftReportRepository.findByCashierAndShiftStartBetween(currentUser,startOfDay, endOfDay);
        if(existing.isPresent())
        {
            throw new Exception("Shift has already started today!");
        }
        Branch branch = currentUser.getBranch();
        ShiftReport shiftReport = ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .branch(branch)
                .build();
        ShiftReport savedReport = shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDto endShift(Long shiftReportId,
                                   LocalDateTime shiftEnd) throws Exception {
        User currentUser = userService.getCurrentUser();
        ShiftReport shiftReport = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser)
                .orElseThrow(() -> new Exception("Shift not found!"));
        shiftReport.setShiftEnd(shiftEnd);
        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(currentUser.getId(),shiftReport.getShiftStart(),shiftReport.getShiftEnd());
        double totalRefunds = refunds.stream().mapToDouble(refund -> refund.getAmount()!=null ?refund.getAmount():0.0).sum();
        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(currentUser,shiftReport.getShiftStart(),shiftReport.getShiftEnd());
        return null;
    }

    @Override
    public ShiftReportDto getShiftReportById(Long id) {
        return null;
    }

    @Override
    public List<ShiftReportDto> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public ShiftReportDto getCurrentShiftProgress(Long cashierId) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        return null;
    }
}
