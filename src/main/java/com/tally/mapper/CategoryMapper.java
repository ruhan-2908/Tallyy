package com.tally.mapper;

import com.tally.model.Category;
import com.tally.payload.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDTO(Category category)
    {
        return CategoryDto.builder()
                .name(category.getName())
                .storeId(category.getStore()!=null ? category.getStore().getId() : null)
                .build();
    }
    public static Category toEntity(CategoryDto categoryDto)
    {
        return Category.builder()
                .name(categoryDto.getName())
                .store(categoryDto.getStore())
                .id(categoryDto.getId())
                .build();
    }
}
