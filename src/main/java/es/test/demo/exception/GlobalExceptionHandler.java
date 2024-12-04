package es.test.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.test.demo.exception.product.ProductNotFoundException;
import es.test.demo.filter.CustomRequestContextFilter;
import es.test.demo.model.ErrorResponseDTO;

/**
 * GlobalExceptionHandler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

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
