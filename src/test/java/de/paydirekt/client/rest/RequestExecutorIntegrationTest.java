package de.paydirekt.client.rest;

import org.apache.http.client.methods.HttpPost;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
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
        assertThat(expectedException.getErrorMessages(), notNullValue());
        assertThat(expectedException.getErrorMessages(), contains(
                hasProperty("severity", is(ErrorSeverity.ERROR))
        ));
    }

}