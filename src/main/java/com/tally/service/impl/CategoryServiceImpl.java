package com.tally.service.impl;

import com.tally.domain.UserRole;
import com.tally.exceptions.UserException;
import com.tally.mapper.CategoryMapper;
import com.tally.model.Category;
import com.tally.model.Store;
import com.tally.model.User;
import com.tally.payload.CategoryDto;
import com.tally.repository.CategoryRepository;
import com.tally.repository.StoreRepository;
import com.tally.service.CategoryService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(categoryDto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found!")
        );

        Category category = Category.builder()
                .store(store)
                .name(categoryDto.getName())
                .build();
        checkAuthority(user,category.getStore());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoryByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category not found!")
        );
        User user = userService.getCurrentUser();
        category.setName(categoryDto.getName());
        checkAuthority(user,category.getStore());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category not found!")
        );
        User user = userService.getCurrentUser();
        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user , Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager)
        {
            throw new Exception("You dont have permission to manage this category");
        }
    }
}
