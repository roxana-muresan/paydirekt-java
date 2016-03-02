package de.paydirekt.client.security;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class NonceTest {

    @Test
    public void thatNonceIsGenerated() {

        String nonce = Nonce.createRandomNonce();

        assertThat(nonce.length(), is(64));
    }

    @Test
    public void thatDifferentNoncesAreGenerated() {

        String nonce1 = Nonce.createRandomNonce();
        String nonce2 = Nonce.createRandomNonce();

        assertThat("Nonce should be different", nonce1, not(nonce2));
    }

}
