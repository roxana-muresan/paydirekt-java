package de.paydirekt.client.security;

import de.paydirekt.client.rest.PostRequestBuilder;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.model.AccessToken;
import de.paydirekt.client.security.model.ObtainTokenRequest;
import org.apache.http.client.methods.HttpPost;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static de.paydirekt.client.rest.EndpointProperties.getTokenObtainEndpoint;
import static java.util.Objects.requireNonNull;

/**
 * This client connects to the security endpoint.
 * This endpoint authenticates a shop.
 */
public class SecurityClient {

    private final String tokenEndpoint;
    private final String apiKey;
    private final String apiSecret;
    private final RequestExecutor requestExecutor;

    /**
     * Constructor. Initializes the tokenEndpoint and the requestExecutor.
     *
     * @param apiKey    The api key of the merchant.
     * @param apiSecret The api secret of the merchant.
     */
    public SecurityClient(String apiKey, String apiSecret) {
        this(getTokenObtainEndpoint(), apiKey, apiSecret, new RequestExecutor());
    }

    /**
     * Constructor.
     *
     * @param tokenEndpoint   The URL of the token obtain endpoint.
     * @param apiKey          The api key of the merchant.
     * @param apiSecret       The api secret of the merchant.
     * @param requestExecutor The request executor.
     */
    public SecurityClient(String tokenEndpoint,
                          String apiKey,
                          String apiSecret,
                          RequestExecutor requestExecutor) {
        requireNonNull(tokenEndpoint);
        requireNonNull(apiKey);
        requireNonNull(apiSecret);
        requireNonNull(requestExecutor);

        this.tokenEndpoint = tokenEndpoint;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.requestExecutor = requestExecutor;
    }

    /**
     * Retrieves an OAuth2 Access Token that authenticates a shop.
     * The token must be provided in order to execute any requests against the merchant API.
     *
     * @return The retrieved accessToken.
     */
    public AccessToken getAccessToken() {
        final HttpPost post = getPostRequest();
        return requestExecutor.executeRequest(post, AccessToken.class);
    }

    private HttpPost getPostRequest() {
        // ingredients: time, request ID and random nonce
        final Instant now = Instant.now();
        final String requestId = UUID.randomUUID().toString();
        final String randomNonce = Nonce.createRandomNonce();
        // calculate the HMAC signature
        final String signature = Hmac.signature(requestId, now, apiKey, apiSecret, randomNonce);
        // use the RFC-1123 date format
        final String rfc1123DateString = DateTimeFormatter.RFC_1123_DATE_TIME.format(
                ZonedDateTime.ofInstant(now, ZoneId.of("GMT")));

        return new PostRequestBuilder(tokenEndpoint)
                .withHeader("X-Date", rfc1123DateString)
                // the self-generated request ID
                .withHeader("X-Request-ID", requestId)
                // the X-Auth-Key is the API key
                .withHeader("X-Auth-Key", apiKey)
                // the X-Auth-Code is the HMAC signature (not the API secret!)
                .withHeader("X-Auth-Code", signature)
                .withHalJsonContentHeaders()
                .withEntity(new ObtainTokenRequest(randomNonce))
                .build();
    }

}
