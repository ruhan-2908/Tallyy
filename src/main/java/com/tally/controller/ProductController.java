package com.tally.controller;


import java.util.List;

import com.tally.model.User;
import com.tally.payload.dto.ProductDto;
import com.tally.payload.response.ApiResponse;
import com.tally.service.ProductService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto,
                                             @RequestHeader("Authorization") String jwt)
            throws Exception
    {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(productService.createProduct(productDto,user));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getByStoreId(@PathVariable Long storeId,
                                                         @RequestHeader("Authorization") String jwt)
            throws Exception
    {
        return ResponseEntity.ok(productService.getProductsByStoreId(storeId));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id,
                                             @RequestBody ProductDto productDto,
                                             @RequestHeader("Authorization") String jwt)
            throws Exception
    {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(productService.updateProduct(id,productDto,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id,
                                              @RequestHeader("Authorization") String jwt)
            throws Exception
    {
        User user   = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(id,user);
        return ResponseEntity.ok(ApiResponse.builder()
                                            .message("Product deleted successfully")
                .build());
    }

    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDto>> search(@PathVariable Long storeId,
                                                   @RequestParam String keyword,
                                                   @RequestHeader("Authorization") String jwt)
            throws Exception
    {
        return ResponseEntity.ok(productService.searchByKeyword(storeId,keyword));
    }

}
