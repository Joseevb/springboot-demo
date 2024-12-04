package es.test.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.test.demo.model.ProductDTO;
import es.test.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * ProductController
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id", required = true) final Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid final ProductDTO product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(product));
    }

}
