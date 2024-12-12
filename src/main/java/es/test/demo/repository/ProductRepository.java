package es.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.test.demo.entity.ProductEntity;

/**
 * {@link ProductRepository}
 * Manages all the connections to the database and the {@link ProductEntity},
 * inheriting the methods from the {@link JpaRepository}
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
