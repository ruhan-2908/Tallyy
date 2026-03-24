package com.tally.payload.dto;

import com.tally.model.Branch;
import com.tally.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private Long id;


    private BranchDto branchDto;

    // we have BranchDto object and branchId because frontend sends req in ids and not objects
    // for branches , so while converting them back to entity, we get the branch from id
    // and store them , also in the entity, the Objects will be converted into ids by JPA

    private Long branchId;

    private ProductDto productDto;

    private Long productId;

    private Integer quantity;

    private LocalDateTime lastUpdate;
}
