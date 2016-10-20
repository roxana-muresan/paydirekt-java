package de.paydirekt.client.security;

import de.paydirekt.client.security.model.AccessToken;
import de.paydirekt.client.testutil.ClientsFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests the functionality of the {@link SecurityClient}
 */
public class SecurityClientIntegrationTest {

    private SecurityClient securityClient;

    @Before
    public void setUp() {
        securityClient = ClientsFactory.newSecurityClient();
    }

    @Test
    public void obtainedAccessTokenShouldBeValid() {
        AccessToken accessToken = securityClient.getAccessToken();

        assertThat(accessToken, notNullValue());
        assertThat(accessToken.getAccess_token(), notNullValue());
        assertThat(accessToken.getExpires_in(), notNullValue());
        assertThat("Access token should be valid more than 60 seconds.",
                accessToken.getExpires_in(), greaterThan(60L));
    }

}
