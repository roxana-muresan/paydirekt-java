package de.paydirekt.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.paydirekt.client.model.AccessToken;
import de.paydirekt.client.model.ObtainTokenRequest;
import de.paydirekt.client.security.Hmac;
import de.paydirekt.client.security.Nonce;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * This test retrieves a valid OAuth2 access token from the paydirekt Sandbox.
 * <p>
 * The test uses HttpClient from Apache HttpComponents (https://hc.apache.org),
 * which is highly recommended in favor of Java's default HttpUrlConnection.
 * <p>
 * By default, this test is mocked, i.e. it uses a stubbed response.
 * To enable a real connection to the Sandbox, set the system property via '-Dremote=true'
 */
public class ObtainTokenIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger("paydirekt");

    private static final String SANDBOX_ENDPOINT = "https://api.sandbox.paydirekt.de/api/merchantintegration/v1/token/obtain";

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        httpClient = isMock() ? mockedHttpClient() : remoteHttpClient();
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    public void obtainTokenFromSandbox() throws Exception {

        // credentials
        final String apiKey = "e81d298b-60dd-4f46-9ec9-1dbc72f5b5df";
        String apiSecret = "GJlN718sQxN1unxbLWHVlcf0FgXw2kMyfRwD0mgTRME=";

        // ingredients: time, request ID and random nonce
        final Instant now = Instant.now();
        final String requestId = UUID.randomUUID().toString();
        final String randomNonce = Nonce.createRandomNonce();

        // calculate the HMAC signature
        final String signature = Hmac.signature(requestId, now, apiKey, apiSecret, randomNonce);

        HttpPost post = new HttpPost(SANDBOX_ENDPOINT);

        // set necessary request headers
        // use the RFC-1123 date format
        String rfc1123DateString = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.ofInstant(now, ZoneId.of("GMT")));
        post.addHeader("X-Date", rfc1123DateString);
        // the self-generated request ID
        post.addHeader("X-Request-ID", requestId);
        // the X-Auth-Key is the API key
        post.addHeader("X-Auth-Key", apiKey);
        // the X-Auth-Code is the HMAC signature (not the API secret!)
        post.addHeader("X-Auth-Code", signature);
        // the content type of the request
        post.addHeader("Content-Type", "application/hal+json;charset=utf-8");
        // the accepted response content type
        post.addHeader("Accept", "application/hal+json");

        // request body
        ObtainTokenRequest obtainTokenRequest = new ObtainTokenRequest(randomNonce);
        String body = objectMapper.writeValueAsString(obtainTokenRequest);
        logger.debug("{} token.obtain.request.body: {}", requestId, body);
        post.setEntity(new StringEntity(body, UTF_8));

        HttpResponse response;
        String responseString;
        try {
            // send the request
            response = httpClient.execute(post);
            // get the response content
            responseString = EntityUtils.toString(response.getEntity(), UTF_8);
            // security advice: never actually write a valid OAuth2 access token to a log file
            //logger.debug("{} token.obtain.response.body: {}", requestId, responseString);
        } catch (IOException e) {
            logger.error("{} token.obtain.exception: IO exception", requestId, e.getMessage());
            throw new RuntimeException("Error connecting to paydirekt endpoint " + SANDBOX_ENDPOINT, e);
        }

        // response handling
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            logger.error("{} token.obtain.exception: Unexpected status code {} with response: {}", requestId, statusCode, responseString);
            throw new RuntimeException("Unexpected status code: " + statusCode);
        }


        // interpret the JSON response content
        AccessToken accessToken = objectMapper.readValue(responseString, AccessToken.class);

        // assertions on success
        assertThat(accessToken, notNullValue());
        assertThat(accessToken.getAccess_token(), notNullValue());
        assertThat(accessToken.getExpires_in(), notNullValue());
        assertThat("Access token should be valid more than 60 seconds.", accessToken.getExpires_in(), greaterThan(60L));
    }

    private boolean isMock() {
        return !Boolean.getBoolean("remote");
    }

    private HttpClient remoteHttpClient() {
        logger.info("Using real http client to connect to the Sandbox.");
        return HttpClients.createDefault();
    }

    private HttpClient mockedHttpClient() throws Exception {
        logger.info("Using mock http client.");
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse mockedResponse = Mockito.mock(HttpResponse.class);
        when(httpClient.execute(any(HttpPost.class)))
                .thenReturn(mockedResponse);
        when(mockedResponse.getEntity())
                .thenReturn(new StringEntity("{\"access_token\":\"j9GNwm3QoPqb4A7iz-kplWnIfkuVbEs7ZHKKOjUAnSbzYJSO8bSYfuPXepM8hrV7PuGObfajZhlX58u63KNw-eDNf2abPxT8HKLVHIf6blsZbkj7BeMdp8iuN0ujmcZ69njx1pzeelo_eStwfOm09KgBP5wcN8c1bh5cuxbcdAMVuNhgjHyyYO8EtWFsInup3tiXbA0l_MGKdQme0NCweNdMZ9lNZ7d5zdkX6OLffcUOZ3VcQDUfHXVAzmdF-LWp9KUmOfhcNBWs-hR7woGZilCpcrSwa9SZj04EYW9uaIPMfqDSzHtLLPxTtOMsRKCs70HuKjZAcOoVzyVdHsV_c4QMXaEtjJ8fyKDbk0zSicUDai-lic7gBptX-fEkeo0z4SdcOXvjez3hy1ZQRG4OThiPZT-HFMIZdVZhO9NN7eP5wLxZpNy_tvu2MymHrbj4MRfdcZZFsZsPOepsPgIlZR4pxHRDwVtyzIcMOd7wu9HrGDC6ZTwRjojkSrRPB8IrMj4CmaRnUy52rikvsv5EAaoImuYpJhg1-rdyUTWNo-bi2agAW9JWwEadaM67EcUlG3PB9bOmNNYkT_J3Y-EWh4vr0anlEwqlEkDaonnEsBYfAOzCFd6YmXsCMKJW5THSqp86qvl3tc92FjdnGc4L08Lkzdenlj2ETrDeUM5zodqOqD0b2AiydlcM_r-T38tOl0ZrUR5hKpz1ms7uIPIcGGY9a0Q7ZxXu-riUwKzCRMHZC97IGOojh-YoPIkFUew2VQHmHT4F_mldURsFwLZ752Z6Gy8Zj1JrXVu9yk72YoIEzWrq-YDuh5wlzEYSYahxnamlR_S4mvSsO0ta7e0ZI4uNitiIhv_80h5_Vo8esXqO2_bBUxAQkMEcAY7w5V0tao7uPZn78l6U8yS1Z8SfWtkwbWzeNf-RByYQWRx29amFjoRx_f9TtDLQuNdlw1Z3A9x0dI_ouDvwior8kk0of7dG4F8ES-sLE6KOBBachZjoXr6eOZ2LI-qNGtJdhaFakpNhCy9U_cLpaAs6hACkYQPkjALajBVByLRt4ILENImzffjk0mpz6CB63drsHmnjFtr6cA7Uy9lp-wkIUVhJ8yFv-reKWFwRH3KyHdufw84ZT2WUxRzICtDxDzVAzGSxGWT3ySGnaA2xKW0k94v_1aFNDLbuQXhP9-ieBX9xQ-YFYf2AwXCSJWcR6roHB7VO3ZQi8R_sq7ZgZNsohptsEs\",\"token_type\":\"bearer\",\"expires_in\":3599,\"scope\":\"checkout\",\"jti\":\"09ef062a-e9dc-4265-bd90-8ece41785534\"}\n"));
        when(mockedResponse.getStatusLine())
                .thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, 200, "OK"));
        return httpClient;
    }

}
