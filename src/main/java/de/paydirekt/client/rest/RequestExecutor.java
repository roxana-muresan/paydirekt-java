package de.paydirekt.client.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * This class encapsulates the HttpClient from Apache HttpComponents (https://hc.apache.org)
 * and provides deserialization of the response body.
 */
public class RequestExecutor {

    private static final Logger logger = LoggerFactory.getLogger(RequestExecutor.class);
    private static final ObjectMapper objectMapper;
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * Executes a request and deserializes the response body into the specified type.
     *
     * @param request      The request to be executed.
     * @param responseType The class of the response type.
     * @param <T>          The response type.
     * @return The deserialized response body.
     */
    public <T> T executeRequest(HttpRequestBase request, Class<T> responseType) {

        String responseString = executeRequest(request);

        //deserialize the body
        try {
            return objectMapper.readValue(responseString, responseType);
        } catch (IOException e) {
            logger.error("Error while deserializing {} to {}", responseString, responseType);
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes a request without trying to parse the response into a domain object,
     * instead the raw response body is returned.
     *
     * @param request The request to be executed.
     * @return The response body as String.
     */
    public String executeRequest(HttpRequestBase request) {

        HttpResponse response;
        String responseString;

        //execute the request
        try {
            response = HTTP_CLIENT.execute(request);
            responseString = EntityUtils.toString(response.getEntity(), UTF_8);
        } catch (IOException e) {
            logger.error("Error while executing request for location {} with method {} and headers {}",
                    request.getURI(), request.getMethod(), request.getAllHeaders());
            throw new RuntimeException(e);
        }

        // response handling
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200 && statusCode != 201 && statusCode != 204) {
            throw new HttpStatusException(statusCode, parseErrorMessages(responseString));
        }

        return responseString;
    }

    private List<ErrorMessage> parseErrorMessages(String responseString) {
        try {
            return objectMapper.readValue(responseString, ErrorMessages.class).getErrorMessages();
        } catch (IOException e) {
            logger.error("Failed to parse ErrorMessages out of {}", responseString);
            return Collections.emptyList();
        }
    }

    /**
     * Container for {@link ErrorMessage} that helps with deserialization.
     */
    private static class ErrorMessages {

        private List<ErrorMessage> errorMessages;

        public ErrorMessages(@JsonProperty("messages") List<ErrorMessage> errorMessages) {
            this.errorMessages = errorMessages;
        }

        public List<ErrorMessage> getErrorMessages() {
            return errorMessages;
        }
    }

}
