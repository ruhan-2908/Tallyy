package com.tally.service;

import java.util.List;
import com.tally.payload.dto.InventoryDto;

public interface InventoryService {

    InventoryDto createInventory(InventoryDto inventoryDto) throws Exception;
    InventoryDto updateInventory(Long id , InventoryDto inventoryDto) throws Exception;
    void deleteInventory(Long id) throws Exception;
    InventoryDto getInventoryById(Long id) throws Exception;
    InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDto> getAllInventoryByBranch(Long branchId);

}
