package es.test.demo.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ErrorResponseDTO
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private HttpStatus status;

    private String error;

    private String message;

    private String path;

    public short getStatus() {
        return (short) status.value();
    }

}
