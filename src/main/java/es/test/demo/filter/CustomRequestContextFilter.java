package es.test.demo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomRequestContextFilter implements Filter {

    private static final ThreadLocal<String> requestPathHolder = new ThreadLocal<>();

    public static String getRequestPath() {
        return requestPathHolder.get();
    }

    public static void clear() {
        requestPathHolder.remove();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        if (request instanceof HttpServletRequest httpRequest) {
            requestPathHolder.set(httpRequest.getRequestURI());
        }
        try {
            chain.doFilter(request, response);
        } catch (ServletException | IOException e) {
            log.error("An error occured: {}", e.getLocalizedMessage());
        } finally {
            clear(); // Ensure thread-local is cleared after the request
        }
    }
}
