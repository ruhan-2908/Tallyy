package com.tally.service;


import com.tally.payload.dto.ProductDto;
import com.tally.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    ProductDto updateProduct(Long id,ProductDto productDto,User user) throws Exception;
    void deleteProduct(Long id,User user) throws Exception;
    List<ProductDto> getProductsByStoreId(Long storeId);
    List<ProductDto> searchByKeyword(Long storeId, String keyword);
}
