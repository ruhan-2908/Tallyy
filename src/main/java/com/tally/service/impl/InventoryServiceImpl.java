package com.tally.service.impl;

import com.tally.mapper.InventoryMapper;
import com.tally.model.Inventory;
import com.tally.model.Product;
import com.tally.payload.dto.InventoryDto;
import com.tally.repository.BranchRepository;
import com.tally.repository.InventoryRepository;
import com.tally.repository.ProductRepository;
import com.tally.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tally.model.Branch;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception{
        Branch branch = branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(
                () -> new Exception("Branch not found!")
        );
        Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                () -> new Exception("Product not found!")
        );
        Inventory inventory = InventoryMapper.toEntity(inventoryDto,branch,product);
        return InventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new Exception("Inventory not found!")
        );
        inventory.setQuantity(inventoryDto.getQuantity());
        return InventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new Exception("Inventory not found!")
        );
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new Exception("Inventory not found!")
        );
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId,branchId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoryByBranch(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
    }
}
