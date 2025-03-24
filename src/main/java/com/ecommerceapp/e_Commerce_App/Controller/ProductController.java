package com.ecommerceapp.e_Commerce_App.Controller;

import com.ecommerceapp.e_Commerce_App.Entity.Product;
import com.ecommerceapp.e_Commerce_App.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get all products",
            description = "This operation retrieves all products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of product list",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)))
            })

    public List<Product> getAll() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get Product by ID",
            description = "Retrieve a product using its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    public Product getById(@PathVariable String id) {
        return productService.getProductById(id);
    }
    @PostMapping
    @Operation(summary = "Create a new product",
            description = "Adds a new product to the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid product data")
            })
    public Product create(@Parameter(description = "Product data to be added",
            required = true,
            schema = @Schema(implementation = Product.class))
            @RequestBody Product product) {
        return productService.saveProduct(product);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product",
            description = "Removes a product from the system",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    public void delete(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
