package de.paydirekt.client.security;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * A nonce is a random arbitrary character sequence that may only be used once.
 */
public final class Nonce {

    /**
     * Properly initialized SHA1PRNG for nonce generation.
     */
    private static final SecureRandom secureRandom;

    static {
        // properly initialize the pseudorandom number generator
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
            // to properly initialize it needs to be used once
            final byte[] ar = new byte[64];
            secureRandom.nextBytes(ar);
            Arrays.fill(ar, (byte) 0);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Private constructor.
     * <p>
     * This class provides static functions only.
     */
    private Nonce() {
    }

    /**
     * Creates a random nonce as a character sequence of length 64.
     *
     * @return The nonce.
     */
    public static String createRandomNonce() {
        final byte[] ar = new byte[48];
        secureRandom.nextBytes(ar);
        final String nonce = new String(java.util.Base64.getUrlEncoder().withoutPadding().encode(ar), StandardCharsets.UTF_8);
        Arrays.fill(ar, (byte) 0);
        return nonce;
    }

}
