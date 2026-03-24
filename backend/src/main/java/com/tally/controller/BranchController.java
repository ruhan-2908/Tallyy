package com.tally.controller;


import com.tally.exceptions.UserException;
import com.tally.model.Branch;
import com.tally.payload.dto.BranchDto;
import com.tally.payload.response.ApiResponse;
import com.tally.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws UserException {
        return ResponseEntity.ok(branchService.createBranch(branchDto,null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesByStoreId
            (@PathVariable Long storeId)
            throws Exception
    {
        List<BranchDto> branches = branchService.getAllBranchesByStoreId(storeId);
        return ResponseEntity.ok(branches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable Long id,
                                                  @RequestBody BranchDto branchDto)
            throws Exception
    {
        BranchDto createdBranch = branchService.updateBranch(id,branchDto,null);
        return ResponseEntity.ok(createdBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id)
            throws Exception
    {
        branchService.deleteBranch(id);
        //sorry for poor readability
        //thought to make it concise
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Branch deleted successfully!")
                .build());
    }
}
