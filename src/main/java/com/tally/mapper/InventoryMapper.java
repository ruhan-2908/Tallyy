package com.tally.mapper;


import com.tally.model.Branch;
import com.tally.model.Inventory;
import com.tally.model.Product;
import com.tally.payload.dto.InventoryDto;

public class InventoryMapper {
    public static InventoryDto toDTO(Inventory inventory)
    {
        return InventoryDto.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .productDto(ProductMapper.toDTO(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .build();
    }
    public static Inventory toEntity(InventoryDto inventoryDto,
                                     Branch branch,
                                     Product product)
    {
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();

    }
}
