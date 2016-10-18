package de.paydirekt.client.testutil;

import de.paydirekt.client.capture.CaptureClient;
import de.paydirekt.client.checkout.CheckoutClient;
import de.paydirekt.client.refund.RefundClient;
import de.paydirekt.client.reports.ReportsClient;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.SecurityClient;

import static de.paydirekt.client.rest.EndpointProperties.SANDBOX_CHECKOUT_ENDPOINT;
import static de.paydirekt.client.rest.EndpointProperties.SANDBOX_TOKEN_OBTAIN_ENDPOINT;
import static de.paydirekt.client.rest.EndpointProperties.SANDBOX_TRANSACTION_REPORTS_ENDPOINT;
import static de.paydirekt.client.testutil.TestProperties.API_KEY;
import static de.paydirekt.client.testutil.TestProperties.API_SECRET;

/**
 * Factory for the creation of clients to be used in tests.
 */
public final class ClientsFactory {

    public static SecurityClient newSecurityClient() {
        return new SecurityClient(SANDBOX_TOKEN_OBTAIN_ENDPOINT, API_KEY, API_SECRET, new RequestExecutor());
    }

    public static CheckoutClient newCheckoutClient() {
        return new CheckoutClient(SANDBOX_CHECKOUT_ENDPOINT, new RequestExecutor());
    }

    public static CaptureClient newCaptureClient() {
        return new CaptureClient(new RequestExecutor());
    }

    public static RefundClient newRefundClient() {
        return new RefundClient(new RequestExecutor());
    }

    public static ReportsClient newReportsClient() {
        return new ReportsClient(SANDBOX_TRANSACTION_REPORTS_ENDPOINT, new RequestExecutor());
    }

    private ClientsFactory() {
    }
}
