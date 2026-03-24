package com.tally.service.impl;

import com.tally.configuration.SecurityConfig;
import com.tally.domain.UserRole;
import com.tally.mapper.UserMapper;
import com.tally.model.Branch;
import com.tally.model.Store;
import com.tally.model.User;
import com.tally.payload.dto.UserDto;
import com.tally.repository.BranchRepository;
import com.tally.repository.StoreRepository;
import com.tally.repository.UserRepository;
import com.tally.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found!")
        );
        Branch branch = null;
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER)
        {
            if(employee.getBranchId()==null)
            {
                throw new Exception("Branch ID is required to create branch manager");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                    ()-> new Exception("Branch not found!")
            );
        }
        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        //encoder bug - resolve and save only hashed one
//        user.setPassword(passwordEncoder(employee.getPassword()));

        User savedEmployee = userRepository.save(user);
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch!=null)
        {
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found!")
        );

        // admin

        if(employee.getRole() == UserRole.ROLE_BRANCH_CASHIER ||
        employee.getRole() == UserRole.ROLE_BRANCH_MANAGER)
        {
            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            //hash it here as well
            user.setPassword(employee.getPassword());
            return UserMapper.toDTO(userRepository.save(user));
        }
        throw new Exception("Branch role not supported");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User existingEmployee = userRepository.findById(employeeId).orElseThrow(
                () -> new Exception("Employee not exist with given id")
        );

        Branch branch = branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                () -> new Exception("Branch not found")
        );
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);
        return userRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = userRepository.findById(employeeId).orElseThrow(
                () -> new Exception("Employee not found!")
        );
        userRepository.delete(employee);
    }

    @Override
    public List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found!")
        );

        return userRepository.findByStore(store).stream().filter(
                user -> role == null || user.getRole()==role
        ).map(UserMapper::toDTO).toList();
    }

    @Override
    public List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found!")
        );

        return userRepository.findByBranchId(branchId)
                .stream().filter(
                        user -> role==null || user.getRole()==role
                ).map(UserMapper::toDTO).toList();
    }
}
