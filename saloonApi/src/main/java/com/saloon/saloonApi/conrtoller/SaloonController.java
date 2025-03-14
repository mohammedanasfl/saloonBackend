package com.saloon.saloonApi.conrtoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saloon.saloonApi.model.ProductList;
import com.saloon.saloonApi.service.SaloonService;

import jakarta.validation.Valid;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PATCH,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}, allowedHeaders = "*")
@RequestMapping("/api/product")
public class SaloonController {

    @Autowired
    private SaloonService saloonService;

    // Fetch all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductList>> getAllProducts() {
        List<ProductList> products = saloonService.getAllProductList();
        return ResponseEntity.ok(products);
    }

    // Create a new product
    @PostMapping("/create")
    public ResponseEntity<ProductList> createProduct(@Valid @RequestBody ProductList productList) {
        ProductList createdProduct = saloonService.createProduct(productList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Update a product
    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductList productList, @PathVariable(name="productId") Integer productId) {
        try {
            String updatedProduct = saloonService.upadateProduct(productList,productId);
            return ResponseEntity.ok(updatedProduct);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        try {
            String response = saloonService.deleteProduct(id);
            return ResponseEntity.ok(Map.of("message", response, "status", HttpStatus.OK));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Product not found", "id", id, "status", HttpStatus.NOT_FOUND));
        }
    }

}
