package com.tally.service.impl;

import com.tally.mapper.ProductMapper;
import com.tally.model.Product;
import com.tally.model.Store;
import com.tally.payload.dto.ProductDto;
import com.tally.model.User;
import com.tally.repository.ProductRepository;
import com.tally.repository.StoreRepository;
import com.tally.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(
                productDto.getStoreId()
        ).orElseThrow(
                () -> new Exception("Store not found")
        );
        Product product = ProductMapper.toEntity(productDto,store);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product Not Found")
        );
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImage(productDto.getImage());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setUpdatedAt(productDto.getUpdatedAt());
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception("Product not found")
        );
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        List<Product> products = productRepository.getProductsByStoreId(storeId);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId,keyword);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
