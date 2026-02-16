package com.tally.controller;


import com.tally.payload.dto.CategoryDto;
import com.tally.payload.response.ApiResponse;
import com.tally.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws Exception {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoryByStoreId(@PathVariable Long storeId)
            throws Exception
    {
        return ResponseEntity.ok(categoryService.getCategoryByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable Long id)
        throws  Exception
    {
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Category deleted successfully!")
                .build());
    }
}
