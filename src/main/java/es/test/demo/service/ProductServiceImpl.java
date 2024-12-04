package es.test.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.test.demo.exception.product.ProductNotFoundException;
import es.test.demo.mapper.ProductMapper;
import es.test.demo.model.ProductDTO;
import es.test.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductServiceImpl
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private static final String NOT_FOUND_EXCEPTION = "Could not find product by that id";

    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::mapProduct)
                .toList();
    }

    @Override
    public ProductDTO getProductById(final int id) {
        return productRepository.findById(id)
                .map(ProductMapper::mapProduct)
                .orElseThrow(() -> new ProductNotFoundException(NOT_FOUND_EXCEPTION));
    }

    @Override
    public ProductDTO addProduct(final ProductDTO product) {
        return ProductMapper.mapProduct(
                productRepository.saveAndFlush(ProductMapper.mapProduct(product)));
    }
}
