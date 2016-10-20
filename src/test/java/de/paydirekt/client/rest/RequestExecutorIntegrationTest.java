package de.paydirekt.client.rest;

import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test the functionality of {@link RequestExecutor}
 */
public class RequestExecutorIntegrationTest {

    private RequestExecutor subject = new RequestExecutor();

    @Test
    public void shouldThrowHttpStatusExceptionOnExecutionOfBadRequest() {

        HttpGet badRequest = new GetRequestBuilder(EndpointProperties.getTokenObtainEndpoint()).build();
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