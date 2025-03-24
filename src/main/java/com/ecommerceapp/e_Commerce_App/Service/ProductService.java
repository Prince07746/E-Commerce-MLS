package com.ecommerceapp.e_Commerce_App.Service;

import com.ecommerceapp.e_Commerce_App.Entity.Product;
import com.ecommerceapp.e_Commerce_App.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public Product getProductById(String id) { return productRepository.findById(id).orElse(null); }
    public Product saveProduct(Product product) { return productRepository.save(product); }
    public void deleteProduct(String id) { productRepository.deleteById(id); }
}