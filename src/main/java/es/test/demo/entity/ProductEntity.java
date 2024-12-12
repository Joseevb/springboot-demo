package es.test.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a product in the database.
 * <p>
 * Maps to the "product" table in the database, where each attribute corresponds
 * to a column.
 * Utilizes Hibernate annotations for ORM mapping and validation annotations for
 * data integrity.
 * <p>
 * This class includes:
 * <ul>
 * <li>{@link javax.persistence.Entity} - Indicates this is a JPA entity.</li>
 * <li>{@link javax.persistence.Table} - Maps to the "product" database
 * table.</li>
 * <li>Validation annotations like {@link javax.validation.constraints.Size},
 * {@link javax.validation.constraints.NotBlank},
 * and {@link javax.validation.constraints.Min} to enforce field
 * constraints.</li>
 * </ul>
 */
@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    /**
     * The unique identifier of the product.
     * <p>
     * Maps to the "id" column in the "product" table and is generated automatically
     * using
     * {@link javax.persistence.GenerationType#IDENTITY}.
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the product.
     * <p>
     * Maps to the "name" column in the "product" table.
     * <ul>
     * <li>Maximum length: 50 characters
     * ({@link javax.validation.constraints.Size}).</li>
     * <li>Cannot be blank ({@link javax.validation.constraints.NotBlank}).</li>
     * </ul>
     */
    @Size(max = 50, message = "The product name cannot be longer than 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    @NotBlank(message = "The product name cannot be blank")
    private String name;

    /**
     * The description of the product.
     * <p>
     * Maps to the "description" column in the "product" table.
     * <ul>
     * <li>Length must be between 20 and 200 characters
     * ({@link javax.validation.constraints.Size}).</li>
     * <li>Cannot be blank ({@link javax.validation.constraints.NotBlank}).</li>
     * </ul>
     */
    @Size(min = 20, max = 200, message = "The product description must be between 20 and 200 characters")
    @Column(name = "description", nullable = false, length = 200)
    @NotBlank(message = "The product description cannot be blank")
    private String description;

    /**
     * The price of the product.
     * <p>
     * Maps to the "price" column in the "product" table.
     * <ul>
     * <li>Cannot be null ({@link javax.validation.constraints.NotNull}).</li>
     * <li>Minimum value: 0 ({@link javax.validation.constraints.Min}).</li>
     * </ul>
     */
    @Min(value = 0, message = "The product price cannot be lower than 0")
    @Column(name = "price", nullable = false)
    @NotNull(message = "The product price cannot be null")
    private Double price;
}
