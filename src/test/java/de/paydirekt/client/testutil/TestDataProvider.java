package de.paydirekt.client.testutil;

import de.paydirekt.client.capture.CaptureClient;
import de.paydirekt.client.capture.model.Capture;
import de.paydirekt.client.capture.model.CaptureRequest;
import de.paydirekt.client.checkout.CheckoutClient;
import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.checkout.model.CheckoutRequest;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.model.AccessToken;

import static de.paydirekt.client.testutil.ClientsFactory.newCaptureClient;
import static de.paydirekt.client.testutil.ClientsFactory.newCheckoutClient;
import static de.paydirekt.client.testutil.ClientsFactory.newSecurityClient;

/**
 * Provides dynamic test fixtures.
 */
public class TestDataProvider {

    private final AccessToken accessToken;
    private final CheckoutClient checkoutClient;
    private final CaptureClient captureClient;
    private final UserAuthorizationClient userAuthorizationClient;

    public TestDataProvider(AccessToken accessToken,
                            CheckoutClient checkoutClient,
                            CaptureClient captureClient,
                            UserAuthorizationClient userAuthorizationClient) {
        this.checkoutClient = checkoutClient;
        this.captureClient = captureClient;
        this.userAuthorizationClient = userAuthorizationClient;
        this.accessToken = accessToken;
    }

    public static TestDataProvider newTestDataProvider() {
        return new TestDataProvider(
                newSecurityClient().getAccessToken(),
                newCheckoutClient(),
                newCaptureClient(),
                new UserAuthorizationClient(new RequestExecutor()));
    }

    public Capture createCapture(CaptureRequest captureRequest, Checkout approvedCheckout) {
        return captureClient.createCapture(
                captureRequest, approvedCheckout, getAccessToken());
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public Checkout createCheckout(CheckoutRequest checkoutRequest) {
        return checkoutClient.createCheckout(checkoutRequest, getAccessToken());
    }

    public Checkout getCheckout(Checkout checkout) {
        return checkoutClient.getCheckout(checkout.getSelfLink(), getAccessToken());
    }

    public Checkout getApprovedCheckout(CheckoutRequest checkoutRequest) {
        Checkout response = createCheckout(checkoutRequest);
        userAuthorizationClient.approveCheckout(response);
        return getCheckout(response);
    }
}
