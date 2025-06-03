package com.project.weezdompet.services;

import com.project.weezdompet.data.entities.CustomerEntity;
import com.project.weezdompet.data.entities.ProductEntity;
import com.project.weezdompet.data.repositories.ProductRepository;
import com.project.weezdompet.web.errors.NotFoundException;
import com.project.weezdompet.web.models.Customer;
import com.project.weezdompet.web.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        Iterable<ProductEntity> productEntities = this.productRepository.findAll();
        List<Product>products = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            products.add(translateDbToWeb(productEntity));
        });
        return products;
    }

    public Product getProduct(long id) {
        Optional<ProductEntity> optional = this.productRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Product createOrUpdateProduct(Product product){
        ProductEntity entity = this.translateWebToDb(product);
        entity = this.productRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }


    private ProductEntity translateWebToDb(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(product.getProductId());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setVendorId(product.getVendorId());

        return productEntity;
    }

    private Product translateDbToWeb(ProductEntity productEntity) {
        return new Product(productEntity.getProductId(), productEntity.getName(), productEntity.getPrice(), productEntity.getVendorId());
    }
}
