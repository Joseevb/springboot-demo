package es.test.demo.service;

import java.util.List;

import es.test.demo.model.ProductDTO;

/**
 * ProductService
 */
public interface ProductService {

    List<ProductDTO> getProducts();

    ProductDTO getProductById(int id);

    ProductDTO addProduct(ProductDTO product);
}
