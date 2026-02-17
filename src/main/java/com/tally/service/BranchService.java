package com.tally.service;

import com.tally.exceptions.UserException;
import com.tally.model.User;
import com.tally.payload.dto.BranchDto;
import java.util.List;

public interface BranchService {
    BranchDto createBranch(BranchDto branchDto, User user) throws UserException;
    List<BranchDto> getAllBranchesByStoreId(Long storeId);
    BranchDto updateBranch(Long branchId, BranchDto branchDto, User user) throws Exception;
    BranchDto getBranchById(Long branchId) throws Exception;
    void deleteBranch(Long branchId) throws Exception;
}
