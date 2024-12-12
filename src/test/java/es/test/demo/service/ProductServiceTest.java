package es.test.demo.service;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import es.test.demo.entity.ProductEntity;
import es.test.demo.exception.product.ProductNotFoundException;
import es.test.demo.mapper.ProductMapper;
import es.test.demo.model.ProductDTO;
import es.test.demo.repository.ProductRepository;

/**
 * ProductServiceTest
 */
public class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDTO productDTO;
    private ProductEntity productEntity;

    @BeforeEach
    public void setup() {
        productEntity = ProductEntity.builder()
                .id(1)
                .name("Test Product")
                .price(1D)
                .description("Test product description")
                .build();

        productDTO = ProductDTO.builder()
                .id(1)
                .name("Test Product")
                .price(1D)
                .description("Test product description")
                .build();

        openMocks(this);
    }

    @Test
    public void when_getProductById_productExists_return_ProductDTO() {
        when(productRepository.findById(1)).thenReturn(Optional.of(productEntity));
        when(productMapper.toDTO(productEntity)).thenReturn(productDTO);

        final ProductDTO response = productService.getProductById(1);

        assertEquals(productDTO, response);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void when_getProductById_productDoesNotExists_throws_ProductNotFoundException() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1));
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void when_addProduct_return_ProductDTO() {
        when(productMapper.toEntity(productDTO)).thenReturn(productEntity);
        when(productRepository.saveAndFlush(productEntity)).thenReturn(productEntity);
        when(productMapper.toDTO(productEntity)).thenReturn(productDTO);

        final ProductDTO response = productService.addProduct(productDTO);

        assertEquals(productDTO, response);
        verify(productRepository, times(1)).saveAndFlush(productEntity);
    }
}
