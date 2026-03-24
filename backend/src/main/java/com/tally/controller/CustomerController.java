package com.tally.controller;


import com.tally.model.Customer;
import com.tally.payload.response.ApiResponse;
import com.tally.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cutomers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer)
    {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id,
                                           @RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Customer deleted successfully")
                .build());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() throws Exception
    {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> search(@RequestParam String q)
            throws  Exception
    {
        return ResponseEntity.ok(customerService.searchCustomers(q));
    }
}
