package com.assigment.inventory_management.controllers;

import com.assigment.inventory_management.models.Product;
import com.assigment.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @GetMapping("/category/{productCat}")
    public ResponseEntity<Optional<List<Product>>> getProduct(@PathVariable String productCat) {
        return ResponseEntity.ok(productRepository.findByCategoryName(productCat));
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        System.out.println("Product added "+ product);
        return ResponseEntity.ok(productRepository.save(product));
    }

    @PostMapping("/bulkLoad")
    public String saveBulkProduct(@RequestBody List<Product> products) {
        System.out.println("Product added "+ products);
        products.forEach(product -> productRepository.save(product));
        return "ok";
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {

        return ResponseEntity.ok(productRepository.save(product));
    }

}
