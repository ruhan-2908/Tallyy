package com.tally.service.impl;

import com.tally.payload.dto.ProductDto;
import com.tally.model.User;
import com.tally.repository.ProductRepository;
import com.tally.service.ProductService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) {
        return null;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) {
        return null;
    }

    @Override
    public void deleteProduct(Long id, User user) {

    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        return List.of();
    }
}
