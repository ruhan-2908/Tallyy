package com.tally.service;

import com.tally.payload.dto.BranchDto;
import java.util.List;

public interface BranchService {
    BranchDto createBranch(BranchDto branchDto);
    List<BranchDto> getBranchesByStoreId(Long storeId);
    BranchDto updateBranch(Long branchId);
    BranchDto getBranchById(Long branchId);
    void deleteBranch(Long branchId);
}
