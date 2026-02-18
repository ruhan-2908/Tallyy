package com.tally.service.impl;

import com.tally.model.Customer;
import com.tally.repository.CustomerRepository;
import com.tally.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer existing = customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found!")
        );
        existing.setFullName(customer.getFullName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer existing = customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found!")
        );
        customerRepository.delete(existing);
    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found!")
        );
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword,keyword);
    }
}
