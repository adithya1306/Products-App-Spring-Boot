package com.adi.myproject.service;

import com.adi.myproject.model.Product;
import com.adi.myproject.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile image) throws IOException {
        product.setId(id);
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    public void deleteProduct(Product productToBeDeleted) {
        repo.delete(productToBeDeleted);
    }

    @Transactional
    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
