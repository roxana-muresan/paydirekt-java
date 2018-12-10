package de.paydirekt.client.rest;

import org.apache.http.client.methods.HttpPost;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test the functionality of {@link RequestExecutor}
 */
public class RequestExecutorIntegrationTest {

    private RequestExecutor subject = new RequestExecutor();

    @Test
    public void shouldThrowHttpStatusExceptionOnExecutionOfBadRequest() {

        HttpPost badRequest = new PostRequestBuilder(EndpointProperties.getTokenObtainEndpoint()).build();
        HttpStatusException expectedException = null;

        try {
            subject.executeRequest(badRequest);
        } catch (HttpStatusException ex) {
            expectedException = ex;
        }

        assertThat(expectedException, notNullValue());
        assertThat(expectedException.getResponse(), notNullValue());
        assertThat(expectedException.getResponse(), containsString("error"));
    }

}