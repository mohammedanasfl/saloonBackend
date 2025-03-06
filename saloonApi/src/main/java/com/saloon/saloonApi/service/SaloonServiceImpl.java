package com.saloon.saloonApi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saloon.saloonApi.model.ProductList;
import com.saloon.saloonApi.repository.SaloonRepository;

@Service
public class SaloonServiceImpl implements SaloonService {
    
    @Autowired
    private SaloonRepository saloonRepository;

    @Override
    public List<ProductList> getAllProductList() {
        return saloonRepository.findAll();
    }

    @Override
    public ProductList createProduct(ProductList productList) {
        return saloonRepository.save(productList);
    }

    @Override
    public String upadateProduct(ProductList productList,Integer productId) {
        Optional<ProductList> existingProductOpt = saloonRepository.findById(productId);

        if (existingProductOpt.isPresent()) {
            ProductList existingProduct = existingProductOpt.get();
            
            existingProduct.setName(productList.getName());
            existingProduct.setPrice(productList.getPrice());
            saloonRepository.save(existingProduct);
            return "Product updated Sucessfully";
        } else {
            throw new NoSuchElementException("Product not found with id: " + productList.getId());
        }
    }


    @Override
    public String deleteProduct(Integer id) {
        if (!saloonRepository.existsById(id)) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
        saloonRepository.deleteById(id);
        return "Deleted successfully";
    }
}
