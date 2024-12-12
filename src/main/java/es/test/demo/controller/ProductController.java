package es.test.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.test.demo.model.ProductDTO;
import es.test.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing products.
 * <p>
 * Exposes endpoints under the base path "/product" for CRUD operations on
 * {@link ProductDTO}.
 * <p>
 * Delegates all business logic to {@link ProductService}.
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return a {@link ResponseEntity} containing an {@link List} of
     *         {@link ProductDTO}
     *         with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProducts());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link ResponseEntity} containing the {@link ProductDTO} with HTTP
     *         status 200 (OK).
     * @throws ProductNotFoundException if no product is found with the specified
     *                                  ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id", required = true) final Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }

    /**
     * Adds a new product.
     *
     * @param product a {@link ProductDTO} containing the details of the product to
     *                add.
     * @return a {@link ResponseEntity} containing the created {@link ProductDTO}
     *         with HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid final ProductDTO product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(product));
    }

    /**
     * Updates an existing product by its ID.
     *
     * @param id      the ID of the product to update.
     * @param product a {@link ProductDTO} containing the updated details of the
     *                product.
     * @return a {@link ResponseEntity} containing the updated {@link ProductDTO}
     *         with HTTP status 200 (OK).
     * @throws ProductNotFoundException if no product is found with the specified
     *                                  ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(
            @PathVariable(name = "id", required = true) final Integer id,
            @RequestBody @Valid final ProductDTO product) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProductById(product, id));
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     * @return a {@link ResponseEntity} with HTTP status 204 (No Content).
     * @throws ProductNotFoundException if no product is found with the specified
     *                                  ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable(name = "id", required = true) final Integer id) {
        productService.deleteProductById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
