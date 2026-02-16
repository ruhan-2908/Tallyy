package com.tally.mapper;

import com.tally.model.Branch
import com.tally.payload.dto.BranchDto;

public class BranchMapper {
    public static BranchDto toDTO(Branch branch)
    {
         return BranchDto.builder()
                 .id(branch.getId())
                 .name(branch.getName())
                 .address(branch.getAddress())
                 .phone(branch.getPhone())
                 .email(branch.getEmail())
                 .workingDays(branch.getWorkingDays())
                 .openTime(branch.getOpenTime())
                 .closeTime(branch.getCloseTime())
                 .createdAt(branch.getCreatedAt())
                 .updatedAt(branch.getUpdatedAt())
                 .storeDto(StoreMapper.toDTO(branch.getStore()))
                 .manager(UserMapper.toDTO(branch.getManager()))
                 .storeId(branch.getStore().getId())
                 .build();
    }
    public static Branch toEntity(BranchDto branchDto)
    {
        return Branch.builder()
                .id(branchDto.getId())
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .phone(branchDto.getPhone())
                .email(branchDto.getEmail())
                .workingDays(branchDto.getWorkingDays())
                .openTime(branchDto.getOpenTime())
                .closeTime(branchDto.getCloseTime())
                .createdAt(branchDto.getCreatedAt())
                .updatedAt(branchDto.getUpdatedAt())
                .store(StoreMapper.toEntity(branchDto.getStoreDto()))
                .manager()
    }
}
