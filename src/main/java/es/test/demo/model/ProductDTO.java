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
 * ProductDTO
 */
@Data
@Builder
@AllArgsConstructor
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @Size(max = 50, message = "The product name cannot be longer than 50 characters")
    @NotBlank(message = "The product name cannot be blank")
    private String name;

    @Size(min = 20, max = 200, message = "The product description must be between 20 and 200 characters")
    @NotBlank(message = "The product description cannot be blank")
    private String description;

    @Min(value = 0, message = "The product price cannot be lower than 0")
    @NotNull(message = "The product price cannot be null")
    private Double price;

}
