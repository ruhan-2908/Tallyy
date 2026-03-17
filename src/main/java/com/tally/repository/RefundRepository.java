package com.tally.repository;

import com.tally.model.Refund;
import com.tally.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByCashierAndCreatedAtBetween(
            User cashier,
            LocalDateTime from,
            LocalDateTime to
    );
    List<Refund> findByCashierId(Long id);
    List<Refund> findByShiftReportId(Long id);
    List<Refund> findByBranchId(Long id);
}
