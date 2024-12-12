package es.test.demo.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing error responses in the API.
 * <p>
 * This class is used to encapsulate error details in a structured format
 * for returning to clients when exceptions occur.
 * <p>
 * Fields include:
 * <ul>
 * <li>{@code timestamp} - The time when the error occurred.</li>
 * <li>{@code status} - The HTTP status associated with the error.</li>
 * <li>{@code error} - The error code (e.g., "404").</li>
 * <li>{@code message} - A descriptive message explaining the error.</li>
 * <li>{@code path} - The URI path where the error occurred.</li>
 * </ul>
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {

    /**
     * The timestamp of when the error occurred.
     * <p>
     * Defaults to the current time when the object is built.
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * The HTTP status of the error.
     * <p>
     * Represents the HTTP status code associated with the error.
     */
    private HttpStatus status;

    /**
     * The error code.
     * <p>
     * A short textual representation of the error (e.g., "404").
     */
    private String error;

    /**
     * A descriptive message explaining the error.
     */
    private String message;

    /**
     * The URI path where the error occurred.
     */
    private String path;

    /**
     * Returns the HTTP status code as a {@code short}.
     * <p>
     * Extracts the numeric value of the {@link HttpStatus} and casts it to a
     * {@code short}.
     *
     * @return the numeric HTTP status code as a {@code short}
     */
    public short getStatus() {
        return (short) status.value();
    }

}
