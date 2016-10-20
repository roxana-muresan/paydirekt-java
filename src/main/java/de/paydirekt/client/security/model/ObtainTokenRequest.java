package de.paydirekt.client.security.model;

import de.paydirekt.client.security.Nonce;

import static java.util.Objects.nonNull;

/**
 * Request body to obtain an access token.
 */
public class ObtainTokenRequest {

    private static final String GRANT_TYPE_API_KEY = "api_key";

    private final String grantType = GRANT_TYPE_API_KEY;
    private final CharSequence randomNonce;

    /**
     * Constructor.
     *
     * @param randomNonce The random nonce to be used in the signature.
     */
    public ObtainTokenRequest(CharSequence randomNonce) {
        nonNull(randomNonce);
        this.randomNonce = randomNonce;
    }

    /**
     * The OAuth2 Grant Type.
     *
     * @return always {@code api_key}.
     */
    public String getGrantType() {
        return grantType;
    }

    /**
     * A random generated value.
     * Use {@link Nonce#createRandomNonce()} to generate the nonce.
     *
     * @return The random nonce to be used in the signature.
     */
    public CharSequence getRandomNonce() {
        return randomNonce;
    }

}
