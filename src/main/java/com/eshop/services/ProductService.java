package com.eshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eshop.dto.ProductDTO;
import com.eshop.entity.Product;
import com.eshop.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product);
    }

    public Product editProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EmptyResultDataAccessException("Product not found", 1));

        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Product not found", 1);
        }
    }
}
