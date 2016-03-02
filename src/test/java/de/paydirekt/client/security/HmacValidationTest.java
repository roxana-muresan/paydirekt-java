package de.paydirekt.client.security;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the input argument validations.
 */
public class HmacValidationTest {

    @Test
    public void nonce_valid() throws Exception {
        Hmac.validateNonce("Qg5f0Q3ly1Cwh5M9zcw57jwHI_HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonce_invalid_null() throws Exception {
        Hmac.validateNonce(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonce_invalid_empty() throws Exception {
        Hmac.validateNonce(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonce_invalid_ToShort() throws Exception {
        Hmac.validateNonce("xxx");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonce_invalid_encoded() throws Exception {
        Hmac.validateNonce("Qg5f0Q3ly1Cwh5M9zcw57jwHI+HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S");
    }


    @Test
    public void uuid_valid() throws Exception {
        assertTrue(Hmac.isUuid("f3fea5f3-60af-496f-ac3e-dbb10924e87a"));
    }

    @Test
    public void uuid_invalid() throws Exception {
        assertFalse(Hmac.isUuid("f3fea5f360af496fac3edbb10924e87a"));
    }


    @Test
    public void base64url_valid() throws Exception {
        assertTrue(Hmac.isBase64UrlEncoded("Qg5f0Q3ly1Cwh5M9zcw57jwHI_HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S"));
    }

    @Test
    public void base64url_invalid_notUrlEncoded() throws Exception {
        assertFalse(Hmac.isBase64UrlEncoded("Qg5f0Q3ly1Cwh5M9zcw57jwHI+HPoKbjdHLurXGpPg0yazdC6OWPpwnYi22bnB6S"));
    }

    @Test
    public void base64url_invalid_only_padding() throws Exception {
        StringBuilder sb = new StringBuilder("=");
        while (sb.length() < 72) {
            assertFalse(Hmac.isBase64UrlEncoded(sb.toString()));
            sb.append('=');
        }

    }


    @Test
    public void apiKey_valid() throws Exception {
        Hmac.validateApiKey("f3fea5f3-60af-496f-ac3e-dbb10924e87a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void apiKey_invalid_null() throws Exception {
        Hmac.validateApiKey(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void apiKey_invalid_empty() throws Exception {
        Hmac.validateApiKey(" ");
    }


    @Test
    public void apiSecret_valid() throws Exception {
        Hmac.validateApiSecret("GJlN718sQxN1unxbLWHVlcf0FgXw2kMyfRwD0mgTRME=");
    }

    @Test(expected = IllegalArgumentException.class)
    public void apiSecret_invalid_null() throws Exception {
        Hmac.validateApiSecret(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void apiSecret_invalid_empty() throws Exception {
        Hmac.validateApiSecret(" ");
    }


    @Test
    public void requestId_valid() throws Exception {
        Hmac.validateRequestId("f3fea5f3-60af-496f-ac3e-dbb10924e87a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void requestId_invalid_null() throws Exception {
        Hmac.validateRequestId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void requestId_invalid_noUuid() throws Exception {
        Hmac.validateRequestId("f3fea5f3");
    }


    @Test
    public void dateString_valid() throws Exception {
        Hmac.validateDateString("20160201094942");
    }

    @Test(expected = IllegalArgumentException.class)
    public void dateString_invalid_null() throws Exception {
        Hmac.validateDateString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dateString_invalid_length() throws Exception {
        Hmac.validateDateString("20160201094942000");
    }

}