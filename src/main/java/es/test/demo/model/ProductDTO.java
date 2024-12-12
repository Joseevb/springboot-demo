package es.test.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a product.
 * <p>
 * This class is used to encapsulate product data for transferring between
 * different layers of the application, excluding database operations.
 * <p>
 * Includes validation annotations to ensure data integrity during creation
 * and updates, and JSON annotations to control serialization behavior.
 * <p>
 * Annotations used:
 * <ul>
 * <li>{@link com.fasterxml.jackson.annotation.JsonProperty} for JSON
 * serialization control.</li>
 * <li>{@link javax.validation.constraints.Size},
 * {@link javax.validation.constraints.NotBlank},
 * and {@link javax.validation.constraints.Min} for validation.</li>
 * </ul>
 */
@Data
@Builder
@AllArgsConstructor
public class ProductDTO {

    /**
     * The unique identifier of the product.
     * <p>
     * This field is read-only and is not intended to be set during creation or
     * updates.
     * <ul>
     * <li>Serialized as "id" in JSON.</li>
     * <li>{@link JsonProperty.Access#READ_ONLY} ensures it is only included in
     * responses.</li>
     * </ul>
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    /**
     * The name of the product.
     * <p>
     * Validation rules:
     * <ul>
     * <li>Cannot be blank ({@link javax.validation.constraints.NotBlank}).</li>
     * <li>Maximum length: 50 characters
     * ({@link javax.validation.constraints.Size}).</li>
     * </ul>
     */
    @Size(max = 50, message = "The product name cannot be longer than 50 characters")
    @NotBlank(message = "The product name cannot be blank")
    private String name;

    /**
     * The description of the product.
     * <p>
     * Validation rules:
     * <ul>
     * <li>Cannot be blank ({@link javax.validation.constraints.NotBlank}).</li>
     * <li>Length must be between 20 and 200 characters
     * ({@link javax.validation.constraints.Size}).</li>
     * </ul>
     */
    @Size(min = 20, max = 200, message = "The product description must be between 20 and 200 characters")
    @NotBlank(message = "The product description cannot be blank")
    private String description;

    /**
     * The price of the product.
     * <p>
     * Validation rules:
     * <ul>
     * <li>Cannot be null ({@link javax.validation.constraints.NotNull}).</li>
     * <li>Minimum value: 0 ({@link javax.validation.constraints.Min}).</li>
     * </ul>
     */
    @Min(value = 0, message = "The product price cannot be lower than 0")
    @NotNull(message = "The product price cannot be null")
    private Double price;
}
