package de.paydirekt.client.rest;

import org.apache.http.client.methods.HttpGet;

/**
 * Builder for {@link org.apache.http.client.methods.HttpGet}
 */
public class GetRequestBuilder extends RequestBuilder<GetRequestBuilder, HttpGet> {

    /**
     * Constructor.
     *
     * @param url The URL of the request.
     */
    public GetRequestBuilder(String url) {
        super(new HttpGet(url));
    }

    @Override
    protected GetRequestBuilder getThis() {
        return this;
    }
}
