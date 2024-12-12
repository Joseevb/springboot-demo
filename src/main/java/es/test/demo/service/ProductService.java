package es.test.demo.service;

import java.util.List;

import es.test.demo.model.ProductDTO;

/**
 * {@link ProductService}
 */
public interface ProductService {

    /**
     * Retrieves a list of all available products.
     *
     * @return a {@link List} of {@link ProductDTO} representing all products.
     */
    List<ProductDTO> getProducts();

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link ProductDTO} representing the product with the specified ID.
     * @throws ProductNotFoundException if no product is found with the specified
     *                                  ID.
     */
    ProductDTO getProductById(int id);

    /**
     * Adds a new product to the system.
     *
     * @param product a {@link ProductDTO} containing the details of the product to
     *                add.
     * @return the added {@link ProductDTO} with its generated ID.
     */
    ProductDTO addProduct(ProductDTO product);

    /**
     * Updates an existing product by its ID.
     *
     * @param product a {@link ProductDTO} containing the updated details of the
     *                product.
     * @param id      the ID of the product to update.
     * @return the updated {@link ProductDTO}.
     * @throws ProductNotFoundException if no product is found with the specified
     *                                  ID.
     */
    ProductDTO updateProductById(ProductDTO product, Integer id);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     * @throws ProductNotFoundException if no product is found with the specified
     *                                  ID.
     */
    void deleteProductById(Integer id);
}
