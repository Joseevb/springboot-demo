package es.test.demo.mapper;

import es.test.demo.entity.ProductEntity;
import es.test.demo.model.ProductDTO;

/**
 * ProductMapper
 */
public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDTO mapProduct(ProductEntity product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static ProductEntity mapProduct(ProductDTO product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
