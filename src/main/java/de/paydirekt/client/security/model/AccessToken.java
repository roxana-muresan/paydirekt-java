package de.paydirekt.client.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An OAuth2 access token as defined in
 * <a href="https://tools.ietf.org/html/rfc6749#section-1.4">RFC 6749 Section 1.4</a>.
 * <p>
 * For all requests to the paydirekt system a valid access token is required to authenticate your shop.
 * The access token is valid for multiple requests to the system, also for different checkouts.
 * <p>
 * The response from the service supplies some more fields, as defined by OAuth2 standard,
 * which are not relevant in our context.
 */
public class AccessToken {

    private final CharSequence access_token;
    private final Long expires_in;

    /**
     * Constructor.
     *
     * @param access_token The OAuth2 access token.
     * @param expires_in   Time in seconds until the access token expires.
     */
    public AccessToken(@JsonProperty("access_token") CharSequence access_token,
                       @JsonProperty("expires_in") Long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    /**
     * The actual OAuth2 access token that must be provided as Bearer in the Authorization header in any subsequent
     * requests to the paydirekt system. The token can and should be used for multiple requests to the system.
     *
     * @return the OAuth2 access token.
     */
    public CharSequence getAccess_token() {
        return access_token;
    }

    /**
     * The time in seconds the {@link #access_token} is valid. After this period a new token must be requested.
     *
     * @return time in seconds.
     */
    public Long getExpires_in() {
        return expires_in;
    }

}
