package com.tally.service.impl;

import com.tally.exceptions.UserException;
import com.tally.mapper.BranchMapper;
import com.tally.model.Branch;
import com.tally.model.Store;
import com.tally.model.User;
import com.tally.payload.dto.BranchDto;
import com.tally.repository.BranchRepository;
import com.tally.repository.StoreRepository;
import com.tally.service.BranchService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final UserService userService;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;

    @Override
    public BranchDto createBranch(BranchDto branchDto, User user) throws UserException {
        User currUser = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(currUser.getId());
        Branch branch = BranchMapper.toEntity(branchDto,store);
        return BranchMapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) {
        return branchRepository.findByStoreId(storeId).stream().map(BranchMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BranchDto updateBranch(Long branchId, BranchDto branchDto, User user) throws Exception {
        Branch existing = branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found!")
        );
        existing.setName(branchDto.getName());
        existing.setWorkingDays(branchDto.getWorkingDays());
        existing.setEmail(branchDto.getEmail());
        existing.setPhone(branchDto.getPhone());
        existing.setAddress(branchDto.getAddress());
        existing.setOpenTime(branchDto.getOpenTime());
        existing.setCloseTime(branchDto.getCloseTime());
        existing.setUpdatedAt(LocalDateTime.now());

        return BranchMapper.toDTO(branchRepository.save(existing));
    }

    @Override
    public BranchDto getBranchById(Long branchId) throws Exception{
        Branch branch  = branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found!")
        );
        return BranchMapper.toDTO(branch);
    }

    @Override
    public void deleteBranch(Long branchId) throws Exception {
        Branch existing = branchRepository.findById(branchId).orElseThrow(
                () -> new Exception("Branch not found!")
        );
        branchRepository.delete(existing);
    }
}
