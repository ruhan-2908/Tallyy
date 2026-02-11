package com.tally.payload;


import com.tally.model.Store;
import com.tally.payload.dto.StoreDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private Store store;
}
