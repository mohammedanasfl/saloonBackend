package com.saloon.saloonApi.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.saloon.saloonApi.exception.ServiceException;
import com.saloon.saloonApi.model.ProductList;
import com.saloon.saloonApi.repository.SaloonRepository;

@Service
public class SaloonServiceImpl implements SaloonService {

    private final SaloonRepository saloonRepository;

    public SaloonServiceImpl(SaloonRepository saloonRepository) {
        this.saloonRepository = saloonRepository;
    }

    @Override
    public List<ProductList> getAllProductList() {
        List<ProductList> products = saloonRepository.findAll();
        if (products.isEmpty()) {
            throw new ServiceException("No products found in the database");
        }
        return products;
    }

    @Override
    public ProductList createProduct(ProductList productList) {
        if (productList.getName() == null || productList.getPrice() == null) {
            throw new ServiceException("Product name and price are required");
        }
        try {
            return saloonRepository.save(productList);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Unable to save product");
        }
    }

    @Override
    public String upadateProduct(ProductList productList, Integer productId) {
        ProductList existingProduct = saloonRepository.findById(productId)
                .orElseThrow(() -> new ServiceException("Product not found with ID: " + productId));

        if (productList.getName() != null) {
            existingProduct.setName(productList.getName());
        }
        if (productList.getPrice() != null) {
            existingProduct.setPrice(productList.getPrice());
        }

        try {
            saloonRepository.save(existingProduct);
            return "Product updated successfully";
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Unable to update product");
        }
    }

    @Override
    public String deleteProduct(Integer id) {
        if (!saloonRepository.existsById(id)) {
            throw new ServiceException("Product not found with ID: " + id);
        }
        try {
            saloonRepository.deleteById(id);
            return "Deleted successfully";
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Unable to delete product");
        }
    }
}
