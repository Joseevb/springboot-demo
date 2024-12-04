package es.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.test.demo.entity.ProductEntity;

/**
 * ProductRepository
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
