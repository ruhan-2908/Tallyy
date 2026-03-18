package com.tally.service;


import com.tally.payload.dto.ShiftReportDto;


import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {
    ShiftReportDto startShift(Long cashierId, Long branchId,
                              LocalDateTime shiftStart) throws Exception;
    ShiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception;
    ShiftReportDto getShiftReportById(Long id) throws Exception;
    List<ShiftReportDto> getAllShiftReports();
    List<ShiftReportDto> getShiftReportsByBranchId(Long branchId);
    List<ShiftReportDto> getShiftReportsByCashierId(Long cashierId);
    ShiftReportDto getCurrentShiftProgress(Long cashierId) throws Exception;
    ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;
}
