package de.paydirekt.client.checkout;

import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.checkout.model.CheckoutRequest;
import de.paydirekt.client.checkout.model.CheckoutStatus;
import de.paydirekt.client.testutil.TestDataProvider;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static de.paydirekt.client.checkout.model.CheckoutStatus.OPEN;
import static de.paydirekt.client.testutil.Assertions.assertLinkPresent;
import static de.paydirekt.client.testutil.ClientsFactory.newCheckoutClient;
import static de.paydirekt.client.testutil.MocksFactory.newCaptureRequest;
import static de.paydirekt.client.testutil.MocksFactory.newDirectSalesCheckoutRequest;
import static de.paydirekt.client.testutil.MocksFactory.newExpressCheckoutRequest;
import static de.paydirekt.client.testutil.MocksFactory.newOrderCheckoutRequest;
import static de.paydirekt.client.testutil.TestDataProvider.newTestDataProvider;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality of the {@link CheckoutClient}
 */
public class CheckoutClientIntegrationTest {

    private CheckoutClient checkoutClient;
    private TestDataProvider testDataProvider;

    @Before
    public void setUp() {
        checkoutClient = newCheckoutClient();
        testDataProvider = newTestDataProvider();
    }

    @Test
    public void shouldCreateValidOrderCheckout() {
        CheckoutRequest checkoutRequest = newOrderCheckoutRequest();

        Checkout createdCheckout = checkoutClient
                .createCheckout(checkoutRequest, testDataProvider.getAccessToken());

        assertCreatedCheckoutValid(createdCheckout, checkoutRequest);
    }

    @Test
    public void shouldCreateValidDirectSalesCheckout() {
        CheckoutRequest checkoutRequest = newDirectSalesCheckoutRequest();

        Checkout createdCheckout = checkoutClient
                .createCheckout(checkoutRequest, testDataProvider.getAccessToken());

        assertCreatedCheckoutValid(createdCheckout, checkoutRequest);
    }

    @Test
    public void shouldCreateValidExpressCheckout() {
        CheckoutRequest checkoutRequest = newExpressCheckoutRequest();

        Checkout createdCheckout = checkoutClient
                .createCheckout(newExpressCheckoutRequest(), testDataProvider.getAccessToken());

        assertCreatedCheckoutValid(createdCheckout, checkoutRequest);
    }

    private void assertCreatedCheckoutValid(Checkout checkout, CheckoutRequest checkoutRequest) {

        assertLinkPresent(checkout, "approve");
        assertLinkPresent(checkout, "self");
        assertThat(checkout.getCheckoutId(), notNullValue());
        assertThat(checkout.getStatus(), is(OPEN));
        assertThat(checkout.getCheckoutId(), notNullValue());
        assertThat(checkout.getItems(), is(checkoutRequest.getItems()));
        assertThat(checkout.getType(), is(checkoutRequest.getType()));
        assertThat(checkout.getTotalAmount(), is(checkoutRequest.getTotalAmount()));
        assertThat(checkout.getShippingAmount(), is(checkoutRequest.getShippingAmount()));
        assertThat(checkout.getOrderAmount(), is(checkoutRequest.getOrderAmount()));
        assertThat(checkout.isOvercapture(), is(checkoutRequest.isOvercapture()));
        assertThat(checkout.getCurrency(), is(checkoutRequest.getCurrency()));
        assertThat(checkout.getShoppingCartType(), is(checkoutRequest.getShoppingCartType()));
        assertThat(checkout.getShippingAddress(), is(checkoutRequest.getShippingAddress()));
        assertThat(checkout.getMerchantCustomerNumber(), is(checkoutRequest.getMerchantCustomerNumber()));
        assertThat(checkout.getMerchantOrderReferenceNumber(), is(checkoutRequest.getMerchantOrderReferenceNumber()));
        assertThat(checkout.getMerchantInvoiceReferenceNumber(),
                is(checkoutRequest.getMerchantInvoiceReferenceNumber()));
        assertThat(checkout.getRedirectUrlAfterAgeVerificationFailure(),
                is(checkoutRequest.getRedirectUrlAfterAgeVerificationFailure()));
        assertThat(checkout.getRedirectUrlAfterCancellation(), is(checkoutRequest.getRedirectUrlAfterCancellation()));
        assertThat(checkout.getRedirectUrlAfterRejection(), is(checkoutRequest.getRedirectUrlAfterRejection()));
        assertThat(checkout.getRedirectUrlAfterSuccess(), is(checkoutRequest.getRedirectUrlAfterSuccess()));
        assertThat(checkout.getCallbackUrlCheckDestinations(), is(checkoutRequest.getCallbackUrlCheckDestinations()));
        assertThat(checkout.getCallbackUrlStatusUpdates(), is(checkoutRequest.getCallbackUrlStatusUpdates()));
        assertThat(checkout.getMinimumAge(), is(checkoutRequest.getMinimumAge()));
        assertThat(checkout.getNote(), is(checkoutRequest.getNote()));
        assertThat(checkout.getWebUrlShippingTerms(), is(checkoutRequest.getWebUrlShippingTerms()));
        assertThat(checkout.getMerchantReconciliationReferenceNumber(),
                is(checkoutRequest.getMerchantReconciliationReferenceNumber()));
    }

    @Test
    public void retrievedCheckoutShouldEqualCreatedCheckout() {
        CheckoutRequest checkoutRequest = newOrderCheckoutRequest();

        Checkout createdCheckout = checkoutClient
                .createCheckout(checkoutRequest, testDataProvider.getAccessToken());
        Checkout retrievedCheckout = checkoutClient.getCheckout(createdCheckout.getSelfLink(),
                testDataProvider.getAccessToken());

        assertThat(createdCheckout, Matchers.is(retrievedCheckout));
    }

    @Test
    public void shouldCloseCheckoutWithCapture() {
        //An approved checkout with at least on capture is a precondition in order to be able to close a checkout
        Checkout approvedCheckout = testDataProvider.getApprovedCheckout(newOrderCheckoutRequest());
        testDataProvider.createCapture(newCaptureRequest(), approvedCheckout);

        Checkout closedCheckout =
                checkoutClient.closeCheckout(
                        testDataProvider.getCheckout(approvedCheckout),
                        testDataProvider.getAccessToken());

        assertThat(closedCheckout.getStatus(), Matchers.is(CheckoutStatus.CLOSED));
        assertThat(closedCheckout.getCheckoutId(), Matchers.is(approvedCheckout.getCheckoutId()));
        assertThat(closedCheckout.getType(), Matchers.is(approvedCheckout.getType()));
        assertThat(closedCheckout.getCreationTimestamp(), Matchers.is(approvedCheckout.getCreationTimestamp()));
    }

}
