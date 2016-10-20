package de.paydirekt.client.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Encodes Strings using the SHA-256 hash algorithm.
 * This is used for fraud prevention.
 */
public final class Sha256Encoder {

    private static final String SHA256_ALGORITHM = "SHA-256";

    /**
     * Encodes Strings using the SHA-256 hash algorithm.
     *
     * @param data The String to encode.
     * @return The encoded String.
     */
    public static String encodeToSha256(String data) {
        byte[] digest = digestToSha256(data.getBytes(UTF_8));
        byte[] base64 = Base64.getEncoder().encode(digest);
        return new String(base64, UTF_8);
    }

    private static byte[] digestToSha256(byte[] data) {
        try {
            return MessageDigest.getInstance(SHA256_ALGORITHM).digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Algorithm not supported", e);
        }
    }

    private Sha256Encoder() {
    }
}
