package de.paydirekt.client.rest;

/**
 * This Exception is thrown when a StatusCode other than 2XX is returned for a request.
 */
public class HttpStatusException extends RuntimeException {

    private final int statusCode;
    private final String response;

    /**
     * Constructor.
     *
     * @param statusCode    The HTTP status code.
     * @param response      The erroneous response.
     */
    public HttpStatusException(int statusCode, String response) {
        super("Unexpected status code: " + statusCode);
        this.statusCode = statusCode;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponse() {
        return response;
    }
}
