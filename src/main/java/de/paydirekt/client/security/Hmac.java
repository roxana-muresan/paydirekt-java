package de.paydirekt.client.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * HMAC: Keyed-Hashing for Message Authentication.
 * <p>
 * https://tools.ietf.org/html/rfc2104
 * <p>
 * paydirekt uses a HMAC-based signature to authenticate a client system of the REST API
 * without transferring a secret over the wire.
 * <p>
 * The signature is created on the client-side
 * by applying HMAC to specific request information (string to sign)
 * using the confidential API secret as key.
 * <p>
 * The signature is verified on the server-side.
 */
public final class Hmac {

    private static final Logger logger = LoggerFactory.getLogger("paydirekt");

    private static final String CRYPTO_ALGORITHM = "HmacSHA256";

    /**
     * Private constructor.
     * <p>
     * This class provides static functions only.
     */
    private Hmac() {
    }

    /**
     * Generate the HMAC signature. The strong SHA-256 algorithm is used.
     *
     * @param requestId The request ID as defined in the request header to identify the message.
     * @param timestamp The current timestamp to ensure that the signature cannot be used again.
     * @param apiKey The API key to identify the shop.
     * @param apiSecret The confidential API secret as provided with the API key.
     * @param randomNonce A random nonce as transferred in the request body to randomize the request. Use {@link Nonce#createRandomNonce()} to generate a random nonce.
     * @return HMAC signature to be used in the header field {@code }X-Auth-Code} in the token obtain endpoint.
     */
    public static String signature(final String requestId, final Instant timestamp, final String apiKey, final String apiSecret, final String randomNonce) {
        final String stringToSign = stringToSign(requestId, timestamp, apiKey, randomNonce);
        return signature(stringToSign, apiSecret);
    }

    /**
     * Build the string to sign, which is required to generate the HMAC signature.
     *
     * @param requestId The request ID as defined in the request header to identify the message.
     * @param timestamp The current timestamp to ensure that the signature cannot be used again.
     * @param apiKey The API key to identify the shop.
     * @param randomNonce A random nonce as transferred in the request body to randomize the request.
     * @return String to sign, used as input in {@link #signature(String, String)}.
     */
    public static String stringToSign(final String requestId, final Instant timestamp, final String apiKey, final String randomNonce) {
        final String signatureDateString = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneId.of("GMT")).format(timestamp);
        return stringToSign(requestId, signatureDateString, apiKey, randomNonce);
    }

    /**
     * Build the string to sign, which is required to generate the HMAC signature.
     *
     * @param requestId The request ID as defined in the request header to identify the message.
     * @param timestampString The current timestamp as string to ensure that the signature cannot be used again. Must be in format {@code yyyyMMddHHmmss} with Timezone GMT.
     * @param apiKey The API key to identify the shop.
     * @param randomNonce A random nonce as transferred in the request body to randomize the request.
     * @return String to sign, used as input in {@link #signature(String, String)}.
     */
    public static String stringToSign(final String requestId, final String timestampString, final String apiKey, final String randomNonce) {

        validateRequestId(requestId);
        validateDateString(timestampString);
        validateApiKey(apiKey);
        validateNonce(randomNonce);

        final String stringToSign = String.format("%s:%s:%s:%s", requestId, timestampString, apiKey, randomNonce);

        logger.info("{} token.obtain.stringToSign: {}", requestId, stringToSign);
        return stringToSign;
    }

    /**
     * Generate the HMAC signature. The strong SHA-256 algorithm is used.
     *
     * @param stringToSign The string to sign which authenticates the message integrity.
     * @param apiSecret The confidential API secret as provided with the API key.
     * @return HMAC signature to be used in the header field <code>X-Auth-Code</code> in the token obtain endpoint.
     */
    public static String signature(final String stringToSign, final String apiSecret) {

        validateApiSecret(apiSecret);

        byte[] keyDecoded = Base64.getUrlDecoder().decode(apiSecret.getBytes(UTF_8));

        String signature;

        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance(CRYPTO_ALGORITHM);
            mac.init(new javax.crypto.spec.SecretKeySpec(keyDecoded, CRYPTO_ALGORITHM));
            signature = new String(Base64.getUrlEncoder().encode(mac.doFinal(stringToSign.getBytes(UTF_8))), UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(String.format("Could not initialize HMAC. %s is not supported by JRE. Is java cryptography extension (JCE) installed?", CRYPTO_ALGORITHM), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key. Check the API secret.", e);
        }

        return signature;
    }

    // --- static methods that are package local for testing purposes ---

    static void validateNonce(final String randomNonce) {
        if (randomNonce == null || randomNonce.trim().isEmpty()) {
            throw new IllegalArgumentException("randomNonce must be a valid, but was empty: " + randomNonce);
        }
        if (randomNonce.length() < 10 || randomNonce.length() > 64) {
            throw new IllegalArgumentException("randomNonce must be a valid, but was: " + randomNonce);
        }
        if (!isBase64UrlEncoded(randomNonce)) {
            throw new IllegalArgumentException("randomNonce must be a valid, but was not base 64 url encoded: " + randomNonce);
        }
    }

    static boolean isUuid(final String string) {
        return string.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");
    }

    static boolean isBase64UrlEncoded(final String string) {
        return string.matches("[a-zA-Z0-9_-]+[=]{0,2}");
    }

    static void validateApiKey(final String apiKey) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("apiKey must be a valid, but was " + apiKey);
        }
        if (!isUuid(apiKey)) {
            throw new IllegalArgumentException("apiKey must be a valid UUID, but was: " + apiKey);
        }
    }

    static void validateApiSecret(final String apiSecret) {
        if (apiSecret == null || apiSecret.trim().isEmpty()) {
            throw new IllegalArgumentException("apiSecret must be a valid, but was not set");
        }
        if (!isBase64UrlEncoded(apiSecret)) {
            throw new IllegalArgumentException("apiSecret must be a valid, but was not base 64 url encoded.");
        }
        if (Base64.getUrlDecoder().decode(apiSecret.getBytes(UTF_8)).length < 32) {
            throw new IllegalArgumentException("apiSecret must be a valid, but was too short.");
        }
    }

    static void validateRequestId(final String requestId) {
        if (requestId == null || requestId.trim().isEmpty()) {
            throw new IllegalArgumentException("requestId must be a valid UUID, but was not set: " + requestId);
        }
        if (!isUuid(requestId)) {
            throw new IllegalArgumentException("requestId must be a valid UUID, but was: " + requestId);
        }
    }

    static void validateDateString(final String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new IllegalArgumentException("dateString must be a valid timestamp in format yyyyMMddHHmmss, but was not set: " + dateString);
        }
        if (dateString.length() != 14) {
            throw new IllegalArgumentException("dateString must be a valid timestamp in format yyyyMMddHHmmss, but was not: " + dateString);
        }
    }

}
