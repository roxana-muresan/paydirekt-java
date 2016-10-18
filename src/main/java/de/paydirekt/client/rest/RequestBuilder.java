package de.paydirekt.client.rest;

import de.paydirekt.client.security.model.AccessToken;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * A builder for the construction of requests.
 *
 * @param <B> The type of the subclass.
 * @param <R> The type of the request.
 */
public abstract class RequestBuilder<B extends RequestBuilder, R extends HttpRequestBase> {

    protected R request;

    /**
     * Constructor.
     *
     * @param request The request to build.
     */
    protected RequestBuilder(R request) {
        this.request = request;
    }

    /**
     * Builds the request.
     *
     * @return The built request.
     */
    public R build() {
        return request;
    }

    /**
     * Retrieves the instance of the subclass.
     *
     * @return The instance of the subclass.
     */
    protected abstract B getThis();

    /**
     * Adds the headers to the request which are necessary for any request against the merchant API.
     *
     * @param accessToken The accessToken.
     * @return The builder.
     */
    public B withDefaultHeaders(AccessToken accessToken) {
        withAuthorizationHeader(accessToken);
        return withHalJsonContentHeaders();
    }

    /**
     * Adds the authorization access token header to the request.
     *
     * @param accessToken The accessToken.
     * @return The builder.
     */
    public B withAuthorizationHeader(AccessToken accessToken) {
        withHeader("Authorization", String.format("Bearer %s", accessToken.getAccess_token()));
        return getThis();
    }

    /**
     * Adds HAL-JSON Content-Type and Accept headers to the request.
     *
     * @return The builder.
     */
    public B withHalJsonContentHeaders() {
        withHeader("Content-Type", "application/hal+json;charset=utf-8");
        withHeader("Accept", "application/hal+json");
        return getThis();
    }

    /**
     * Adds a header to the request.
     *
     * @param header The type of the header.
     * @param value  The value of the header.
     * @return The builder.
     */
    public B withHeader(String header, String value) {
        request.addHeader(header, value);
        return getThis();
    }

}
