package de.paydirekt.client.common;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit Test for {@link Sha256Encoder}
 */
public class Sha256EncoderTest {

    private static final String INPUT = "max@muster.de";
    private static final String EXPECTED_OUTPUT = "6JL4VUgVxkq2m+a9I6ScfW2ofJP5y6wsvSaHIsX+iLs=";

    @Test
    public void shouldEncodeToSHA256() {
        String result = Sha256Encoder.encodeToSha256(INPUT);
        assertThat(result, is(EXPECTED_OUTPUT));
    }

}