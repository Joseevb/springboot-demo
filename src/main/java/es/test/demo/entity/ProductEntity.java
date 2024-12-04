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
 * ProductEntity
 */
@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50, message = "The product name cannot be longer than 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    @NotBlank(message = "The product name cannot be blank")
    private String name;

    @Size(min = 20, max = 200, message = "The product description must be between 20 and 200 characters")
    @Column(name = "description", nullable = false, length = 200)
    @NotBlank(message = "The product description cannot be blank")
    private String description;

    @Min(value = 0, message = "The product price cannot be lower than 0")
    @Column(name = "price", nullable = false)
    @NotNull(message = "The product price cannot be null")
    private Double price;

}
