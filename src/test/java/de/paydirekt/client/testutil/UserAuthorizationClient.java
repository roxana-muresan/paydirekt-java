package de.paydirekt.client.testutil;

import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.rest.GetRequestBuilder;
import de.paydirekt.client.rest.PostRequestBuilder;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.model.AccessToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import static de.paydirekt.client.testutil.TestProperties.CHECKOUT_CONFIRM_ENDPOINT;
import static de.paydirekt.client.testutil.TestProperties.TEST_USER_BASIC_AUTH_HEADER;
import static de.paydirekt.client.testutil.TestProperties.TEST_USER_HASHED_PW;
import static de.paydirekt.client.testutil.TestProperties.TEST_USER_NAME;
import static de.paydirekt.client.testutil.TestProperties.USER_TOKEN_OBTAIN_ENDPOINT;

/**
 * This class automates the login of a user and the approval of a checkout.
 * This is only necessary to automate testing.
 */
public class UserAuthorizationClient {

    private final RequestExecutor requestExecutor;

    public UserAuthorizationClient(RequestExecutor requestExecutor) {
        this.requestExecutor = requestExecutor;
    }

    public void approveCheckout(Checkout checkoutResponse) {
        //get the user access token
        AccessToken token = getUserToken(checkoutResponse.getCheckoutId());
        //get a checkout with the access token
        String getUrl = checkoutResponse.getSelfLink().getHref();
        HttpGet get = new GetRequestBuilder(getUrl)
                .withDefaultHeaders(token)
                .build();
        requestExecutor.executeRequest(get, Checkout.class);
        //confirm the checkout
        String confirmUrl = CHECKOUT_CONFIRM_ENDPOINT.replace("{checkoutId}", checkoutResponse.getCheckoutId());
        HttpPost post = new PostRequestBuilder(confirmUrl)
                .withDefaultHeaders(token)
                .build();
        requestExecutor.executeRequest(post);
    }

    private AccessToken getUserToken(String checkoutId) {
        final HttpPost post = new PostRequestBuilder(USER_TOKEN_OBTAIN_ENDPOINT)
                .withHalJsonContentHeaders()
                .withHeader("Authorization", TEST_USER_BASIC_AUTH_HEADER)
                .withEntity(new ObtainUserTokenRequest(checkoutId, TEST_USER_HASHED_PW, TEST_USER_NAME))
                .build();

        return requestExecutor.executeRequest(post, AccessToken.class);
    }

}
