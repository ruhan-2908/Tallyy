package com.tally.mapper;

import com.tally.model.Category;
import com.tally.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDTO(Category category)
    {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null ? category.getStore().getId() : null)
                .build();
    }
}
