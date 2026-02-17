package com.tally.controller;

import com.tally.model.Inventory;
import com.tally.payload.dto.InventoryDto;
import com.tally.payload.response.ApiResponse;
import com.tally.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDto> create(@RequestBody InventoryDto inventoryDto) throws Exception {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@PathVariable Long id,
                                                        @RequestBody InventoryDto inventoryDto) throws Exception {
        return ResponseEntity.ok(inventoryService.updateInventory(id,inventoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Inventory Deleted Successfully!")
                .build());
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(
            @PathVariable Long branchId
    )throws  Exception
    {
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranch(branchId));
    }

    @GetMapping("/branch/{branchId}/product/{productId}/")
    public ResponseEntity<InventoryDto> getInventoryByProductAndBranchId
            (@PathVariable Long branchId,
             @PathVariable Long productId)
    {
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));
    }
}
