package com.danvinicius.ecommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danvinicius.ecommerce.dto.product.CreateProductRequestDTO;
import com.danvinicius.ecommerce.dto.product.ProductDTO;
import com.danvinicius.ecommerce.dto.product.UpdateProductRequestDTO;
import com.danvinicius.ecommerce.entities.product.Product;
import com.danvinicius.ecommerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(new ProductDTO(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
        List<Product> products = productService
                .getAllProducts(pageable);
        return ResponseEntity.ok().body(products.stream().map(ProductDTO::new)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO data) {
        Product product = productService.createProduct(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product));
    }

    @PostMapping("{productId}/category/{categoryId}")
    public ResponseEntity<ProductDTO> addCategoryToProduct(@PathVariable String productId,
            @PathVariable String categoryId) {
        Product product = productService.addCategoryToProduct(productId, categoryId);
        return ResponseEntity.ok().body(new ProductDTO(product));
    }

    @DeleteMapping("{productId}/category/{categoryId}")
    public ResponseEntity<ProductDTO> removeCategoryFromProduct(@PathVariable String productId,
            @PathVariable String categoryId) {
        Product product = productService.removeCategoryFromProduct(productId, categoryId);
        return ResponseEntity.ok().body(new ProductDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequestDTO data) {
        Product product = productService.updateProduct(id, data);
        return ResponseEntity.ok().body(new ProductDTO(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
