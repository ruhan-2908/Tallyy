package com.tally.mapper;

import com.tally.model.Branch;
import com.tally.model.Store;
import com.tally.payload.dto.BranchDto;

import java.time.LocalDateTime;

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
                 .storeId(branch.getStore()!=null? branch.getStore().getId() : null)
                 .build();
    }

    // no branchDto.id -> branch.id because we use this toEntity for only once , in the create-controller
    // and since the id is auto generated , we don't need to map then (while creation).
    // this applies to all the toEntity without id->id mapping

    public static Branch toEntity(BranchDto branchDto, Store store)
    {
        return Branch.builder()
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .phone(branchDto.getPhone())
                .email(branchDto.getEmail())
                .workingDays(branchDto.getWorkingDays())
                .openTime(branchDto.getOpenTime())
                .closeTime(branchDto.getCloseTime())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .store(store)
                .build();
    }
}
