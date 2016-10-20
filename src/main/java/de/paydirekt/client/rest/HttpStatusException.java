package de.paydirekt.client.rest;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * This Exception is thrown when a StatusCode other than 2XX is returned for a request.
 */
public class HttpStatusException extends RuntimeException {

    private final int statusCode;
    private final List<ErrorMessage> errorMessages;

    /**
     * Constructor.
     *
     * @param statusCode    The HTTP status code.
     * @param errorMessages The messages detailing the error.
     */
    public HttpStatusException(int statusCode, List<ErrorMessage> errorMessages) {
        super("Unexpected status code: " + statusCode);
        this.statusCode = statusCode;
        this.errorMessages = unmodifiableList(errorMessages);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
