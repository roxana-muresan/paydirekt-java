package de.paydirekt.client.security;

import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class HmacTest {

    @Test
    public void thatSignatureIsCorrect() {
        String requestId = "f3fea5f3-60af-496f-ac3e-dbb10924e87a";
        Instant timestamp = Instant.parse("2016-02-01T09:49:42.433Z");
        String apiKey = "e81d298b-60dd-4f46-9ec9-1dbc72f5b5df";
        String apiSecret = "JrXRHCnUegQJAYSJ5J6OvEuOUOpy2q2-MHPoH_IECRY=";
        String randomNonce = "Qg5f0Q3ly1Cwh5M9zcw57jwHI_HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S";

        String signature = Hmac.signature(requestId, timestamp, apiKey, apiSecret, randomNonce);

        assertThat("Signature should match", signature, is("ps9MooGiTeTXIkPkUWbHG4rlF3wuTJuZ9qcMe-Y41xE="));
    }

    @Test
    public void thatStringToSignIsCorrectlyConcatenated() throws Exception {
        String requestId = "f3fea5f3-60af-496f-ac3e-dbb10924e87a";
        Instant timestamp = Instant.parse("2016-02-01T09:49:42.433Z");
        String apiKey = "e81d298b-60dd-4f46-9ec9-1dbc72f5b5df";
        String randomNonce = "Qg5f0Q3ly1Cwh5M9zcw57jwHI_HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S";

        String stringToSign = Hmac.stringToSign(requestId, timestamp, apiKey, randomNonce);

        String expected = "f3fea5f3-60af-496f-ac3e-dbb10924e87a:20160201094942:e81d298b-60dd-4f46-9ec9-1dbc72f5b5df:Qg5f0Q3ly1Cwh5M9zcw57jwHI_HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S";
        assertThat("String to sign should match", stringToSign, is(expected));
    }

    @Test
    public void thatSignatureFailsWithInvalidSecret() {
        String stringToSign = "f3fea5f3-60af-496f-ac3e-dbb10924e87a:20160201094942:e81d298b-60dd-4f46-9ec9-1dbc72f5b5df:Qg5f0Q3ly1Cwh5M9zcw57jwHI_HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S";
        String tooShortApiSecret = "c29tZXRleHQ=";

        try {
            Hmac.signature(stringToSign, tooShortApiSecret);
            fail("should fail");
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

}