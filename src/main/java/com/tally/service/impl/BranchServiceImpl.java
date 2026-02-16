package com.tally.service.impl;

import com.tally.mapper.BranchMapper;
import com.tally.model.Branch;
import com.tally.payload.dto.BranchDto;
import com.tally.repository.BranchRepository;
import com.tally.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        Branch savedBranch = branchRepository.save(BranchMapper.toEntity(branchDto));

        return BranchMapper.toDTO(savedBranch);
    }

    @Override
    public List<BranchDto> getBranchesByStoreId(Long storeId) {
        List<Branch> branches = branchRepository.getBranchesByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BranchDto updateBranch(Long branchId) {

        return null;
    }

    @Override
    public BranchDto getBranchById(Long branchId) {
        return null;
    }

    @Override
    public void deleteBranch(Long branchId) {

    }
}
