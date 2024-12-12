package es.test.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import es.test.demo.exception.product.ProductNotFoundException;
import es.test.demo.mapper.ProductMapper;
import es.test.demo.model.ProductDTO;
import es.test.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link ProductService}.
 * <p>
 * Contains all business logic for managing {@link ProductDTO} objects. Handles
 * database interactions through the {@link ProductRepository}, which extends
 * {@link JpaRepository}.
 * <p>
 * <p>
 * Uses {@link ProductMapper} for mapping between {@link ProductEntity} and
 * {@link ProductDTO} objects. All operations are logged using
 * {@link lombok.extern.slf4j.Slf4j}.
 * <p>
 * This class assumes the following behaviors:
 * <ul>
 * <li>If a product is not found by its ID, an {@link ProductNotFoundException}
 * is thrown.</li>
 * <li>Deletion and updates rely on the existence of the target product.</li>
 * </ul>
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    private static final String NOT_FOUND_EXCEPTION = "Could not find product by that id";

    @Override
    public List<ProductDTO> getProducts() {
        return productMapper.toDTO(
                productRepository.findAll()
                        .stream()
                        .toList());
    }

    @Override
    public ProductDTO getProductById(final int id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new ProductNotFoundException(NOT_FOUND_EXCEPTION));
    }

    @Override
    public ProductDTO addProduct(final ProductDTO product) {
        return productMapper.toDTO(
                productRepository.saveAndFlush(productMapper.toEntity(product)));
    }

    @Override
    public ProductDTO updateProductById(ProductDTO product, Integer id) {
        return productRepository.findById(id)
                .map(_ -> productMapper.toDTO(productRepository.saveAndFlush(productMapper.toEntity(product))))
                .orElseThrow(() -> new ProductNotFoundException(NOT_FOUND_EXCEPTION));
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ProductNotFoundException(NOT_FOUND_EXCEPTION);
        });
    }
}
