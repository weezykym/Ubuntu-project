package com.project.weezdompet.web.rest;

import com.project.weezdompet.services.ProductService;
import com.project.weezdompet.web.errors.BadRequestException;
import com.project.weezdompet.web.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        return this.productService.getProduct(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return this.productService.createOrUpdateProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        if (id != product.getProductId() ) {
            throw new BadRequestException("Product id mismatch");
        }
        return this.productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteProduct(@PathVariable("id") long id) {
        this.productService.deleteProduct(id);
    }
}
