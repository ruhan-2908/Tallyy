package com.tally.controller;


import com.tally.domain.UserRole;
import com.tally.model.User;
import com.tally.payload.dto.UserDto;
import com.tally.payload.response.ApiResponse;
import com.tally.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(@RequestBody UserDto userDto,
                                                       @PathVariable Long storeId)
            throws Exception
    {
        UserDto employee = employeeService.createStoreEmployee(userDto,storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(@RequestBody UserDto userDto,
                                                        @PathVariable Long branchId)
            throws Exception
    {
        UserDto employee = employeeService.createBranchEmployee(userDto,branchId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(@RequestBody UserDto userDto,
                                                  @PathVariable Long id)
            throws Exception
    {
        User employee = employeeService.updateEmployee(id,userDto);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id)
            throws  Exception
    {
        employeeService.deleteEmployee(id);
        return  ResponseEntity.ok(ApiResponse.builder()
                .message("Employee Deleted!")
                .build());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<UserDto>> storeEmployee (@PathVariable Long storeId,
                                               @RequestParam(required = false)UserRole userRole)
    throws Exception{
        List<UserDto> employee = employeeService.findStoreEmployees(storeId,userRole);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<UserDto>> branchEmployee(@PathVariable Long branchId,
                                                     @RequestParam(required = false)UserRole userRole)
        throws Exception
    {
        List<UserDto> employee = employeeService.findBranchEmployees(branchId,userRole);
        return ResponseEntity.ok(employee);
    }
}
