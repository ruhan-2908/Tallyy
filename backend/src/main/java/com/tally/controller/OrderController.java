package com.tally.controller;

import com.tally.domain.OrderStatus;
import com.tally.domain.PaymentType;
import com.tally.payload.dto.OrderDto;
import com.tally.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) throws Exception
    {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception
    {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrdersByBranch(@PathVariable Long branchId,
                                                            @RequestParam(required = false) Long customerId,
                                                            @RequestParam(required = false) Long cashierId ,
                                                            @RequestParam(required = false) PaymentType paymentType,
                                                            @RequestParam(required = false) OrderStatus status) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId,customerId,cashierId,paymentType,status));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByCashier(@PathVariable Long id) throws Exception
    {
        return ResponseEntity.ok(orderService.getOrderByCashier(id));
    }

    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDto>> getTodaysOrder ( @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> getCustomersOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(id));
    }
}
