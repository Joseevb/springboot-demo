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

/**
 * Custom filter to capture the request path for logging or exception handling.
 * <p>
 * This filter extracts the request URI from the incoming HTTP request and
 * stores it in a {@link ThreadLocal}.
 * The request path can then be accessed via {@link #getRequestPath()} and is
 * used for logging or building error responses.
 * <p>
 * After the request is processed, the {@link ThreadLocal} is cleared to avoid
 * memory leaks.
 * <p>
 * The filter implements {@link Filter} to process HTTP requests before passing
 * them to the next filter in the chain.
 * <p>
 * Annotations:
 * <ul>
 * <li>{@link Component} - Registers this filter as a Spring bean.</li>
 * <li>{@link Slf4j} - Enables logging for exception handling.</li>
 * </ul>
 */
@Slf4j
@Component
public class CustomRequestContextFilter implements Filter {

    /**
     * A {@link ThreadLocal} variable to store the request path for the current
     * thread.
     * <p>
     * This allows the request path to be accessed from any part of the code that
     * runs in the same thread
     * while ensuring the path is cleared after the request is processed.
     */
    private static final ThreadLocal<String> requestPathHolder = new ThreadLocal<>();

    /**
     * Retrieves the request path stored in the {@link ThreadLocal}.
     * <p>
     * This method is used by other components (such as exception handlers) to
     * access the URI of the current request.
     *
     * @return the request path or {@code null} if no path is stored
     */
    public static String getRequestPath() {
        return requestPathHolder.get();
    }

    /**
     * Clears the request path from the {@link ThreadLocal}.
     * <p>
     * This method is called after processing the request to ensure that the
     * thread-local variable is cleared
     * and does not cause memory leaks.
     */
    public static void clear() {
        requestPathHolder.remove();
    }

    /**
     * Captures the request URI and stores it in a {@link ThreadLocal} variable
     * before proceeding with the request chain.
     * <p>
     * This method is invoked for every incoming HTTP request. It stores the request
     * URI in {@link #requestPathHolder},
     * allowing the path to be accessed later (e.g., in exception handlers).
     * <p>
     * After the request is processed, {@link #clear()} is called to remove the
     * stored request path from the thread-local variable.
     *
     * @param request  the incoming {@link ServletRequest}
     * @param response the outgoing {@link ServletResponse}
     * @param chain    the {@link FilterChain} to pass the request and response to
     *                 the next filter or servlet
     * @throws IOException      if an I/O error occurs during the request filtering
     *                          process
     * @throws ServletException if a servlet exception occurs during the request
     *                          filtering process
     */
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
