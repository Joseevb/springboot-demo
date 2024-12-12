package es.test.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.test.demo.exception.product.ProductNotFoundException;
import es.test.demo.filter.CustomRequestContextFilter;
import es.test.demo.model.ErrorResponseDTO;

/**
 * Global exception handler for handling application-wide exceptions.
 * <p>
 * This class provides centralized exception handling using Spring's
 * {@link ControllerAdvice} annotation.
 * It ensures consistent error responses for exceptions thrown during request
 * handling.
 * <p>
 * Includes:
 * <ul>
 * <li>{@link ControllerAdvice} - Marks this class as a global exception
 * handler.</li>
 * <li>{@link ExceptionHandler} - Specifies methods to handle specific
 * exceptions.</li>
 * </ul>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ProductNotFoundException} and returns a structured error
     * response.
     * <p>
     * Generates a {@link ErrorResponseDTO} containing details of the error:
     * <ul>
     * <li><b>Error:</b> The HTTP error code (e.g., "404").</li>
     * <li><b>Status:</b> The {@link HttpStatus} associated with the exception.</li>
     * <li><b>Message:</b> The localized error message from the exception.</li>
     * <li><b>Path:</b> The request path where the exception occurred, retrieved
     * from
     * {@link CustomRequestContextFilter#getRequestPath()}.</li>
     * </ul>
     * <p>
     * HTTP Status: 404 (NOT_FOUND).
     *
     * @param e the {@link ProductNotFoundException} to handle
     * @return a {@link ResponseEntity} containing a {@link ErrorResponseDTO} with
     *         error details
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(final ProductNotFoundException e) {
        final String path = CustomRequestContextFilter.getRequestPath();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .error(HttpStatus.NOT_FOUND.toString().split(" ")[1])
                        .status(HttpStatus.NOT_FOUND)
                        .message(e.getLocalizedMessage())
                        .path(path)
                        .build());
    }

}
