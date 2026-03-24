package com.tally.controller;

import com.tally.model.Refund;
import com.tally.payload.dto.RefundDto;
import com.tally.service.RefundService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    @PostMapping()
    public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) throws Exception
    {
        RefundDto refund = refundService.createRefund(refundDto);
        return ResponseEntity.ok(refund);
    }

    @GetMapping()
    public ResponseEntity<List<RefundDto>> getAllRefunds() throws  Exception
    {
        List<RefundDto> refund = refundService.getAllRefunds();
        return ResponseEntity.ok(refund);
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<RefundDto>> getRefundByCashier(@PathVariable Long id)
            throws  Exception
    {
        List<RefundDto> refundDtoList = refundService.getRefundByCashier(id);
        return ResponseEntity.ok(refundDtoList);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<RefundDto>> getRefundByBranch (@PathVariable Long id)
            throws  Exception
    {
        List<RefundDto> refund = refundService.getRefundByBranch(id);
        return ResponseEntity.ok(refund);
    }


    @GetMapping("/shift/{id}")
    public ResponseEntity<List<RefundDto>> getRefundByShift (@PathVariable Long id)
            throws  Exception
    {
        List<RefundDto> refund = refundService.getRefundByShiftReport(id);
        return ResponseEntity.ok(refund);
    }

    @GetMapping("/cashier/{cashierId}/range")
    public ResponseEntity<List<RefundDto>> getRefundByCashierAndDateRange(
            @PathVariable Long cashierId,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate)
            throws  Exception
    {
        List<RefundDto> refund = refundService.getRefundByCashierAndDateRange(
                cashierId,
                startDate,
                endDate
        );
        return ResponseEntity.ok(refund);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDto> getRefundById (@PathVariable Long id)
            throws  Exception
    {
        RefundDto refund = refundService.getRefundById(id);
        return ResponseEntity.ok(refund);
    }
}
