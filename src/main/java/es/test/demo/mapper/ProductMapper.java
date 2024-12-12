package es.test.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import es.test.demo.entity.ProductEntity;
import es.test.demo.model.ProductDTO;

/**
 * Mapper interface for converting between {@link ProductEntity} and
 * {@link ProductDTO}.
 * <p>
 * This interface is implemented by MapStruct at compile-time, and it provides
 * methods
 * for mapping a single {@link ProductEntity} to a {@link ProductDTO}, a list of
 * {@link ProductEntity} objects to a list of {@link ProductDTO}, and vice
 * versa.
 * </p>
 * <p>
 * The generated implementation will be used by Spring as a bean due to the
 * {@code componentModel = "spring"} configuration, allowing it to be injected
 * into services and other components.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    /**
     * Converts a {@link ProductEntity} to a {@link ProductDTO}.
     *
     * @param productEntity the {@link ProductEntity} to be converted
     * @return the corresponding {@link ProductDTO}
     */
    ProductDTO toDTO(ProductEntity productEntity);

    /**
     * Converts a list of {@link ProductEntity} objects to a list of
     * {@link ProductDTO} objects.
     *
     * @param productEntities the list of {@link ProductEntity} objects to be
     *                        converted
     * @return a list of corresponding {@link ProductDTO} objects
     */
    List<ProductDTO> toDTO(List<ProductEntity> productEntities);

    /**
     * Converts a {@link ProductDTO} to a {@link ProductEntity}.
     *
     * @param productDTO the {@link ProductDTO} to be converted
     * @return the corresponding {@link ProductEntity}
     */
    ProductEntity toEntity(ProductDTO productDTO);
}
