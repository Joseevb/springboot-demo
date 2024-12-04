package es.test.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HttpException
 */
@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@AllArgsConstructor
public class HttpException extends RuntimeException {

    private String path;

    private String message;

}
