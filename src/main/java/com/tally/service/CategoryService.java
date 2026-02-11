package com.tally.service;

import com.tally.exceptions.UserException;
import com.tally.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
    List<CategoryDto> getCategoryByStore(Long storeId);
    CategoryDto updateCategory(Long id,CategoryDto categoryDto) throws Exception;
    void deleteCategory(Long id) throws Exception;
}
