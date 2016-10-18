package de.paydirekt.client.refund;

import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.refund.model.Refund;
import de.paydirekt.client.refund.model.RefundRequest;
import de.paydirekt.client.refund.model.RefundStatus;
import de.paydirekt.client.testutil.TestDataProvider;
import org.junit.Before;
import org.junit.Test;

import static de.paydirekt.client.testutil.ClientsFactory.newRefundClient;
import static de.paydirekt.client.testutil.MocksFactory.newCaptureRequest;
import static de.paydirekt.client.testutil.MocksFactory.newOrderCheckoutRequest;
import static de.paydirekt.client.testutil.MocksFactory.newRefundRequest;
import static de.paydirekt.client.testutil.TestDataProvider.newTestDataProvider;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the functionality of the {@link RefundClient}
 */
public class RefundClientIntegrationTest {

    private RefundClient refundClient;
    private TestDataProvider testDataProvider;
    private Checkout approvedCheckout;

    @Before
    public void setUp() {
        refundClient = newRefundClient();
        testDataProvider = newTestDataProvider();
        //An approved checkout with at least on capture is a precondition in order to be able to make a refund
        approvedCheckout = testDataProvider.getApprovedCheckout(newOrderCheckoutRequest());
        testDataProvider.createCapture(newCaptureRequest(), approvedCheckout);
    }

    @Test
    public void retrievedRefundShouldBeEqualToCreatedRefund() {
        RefundRequest refundRequest = newRefundRequest();

        Refund createdRefund = refundClient.createRefund(
                refundRequest, testDataProvider.getCheckout(approvedCheckout),
                testDataProvider.getAccessToken());
        Refund retrievedRefund = refundClient.getRefund(createdRefund.getSelfLink(),
                testDataProvider.getAccessToken());

        assertThat(createdRefund, is(retrievedRefund));
    }

    @Test
    public void shouldCreateValidRefund() {
        RefundRequest refundRequest = newRefundRequest();

        Refund refund = refundClient.createRefund(
                refundRequest, testDataProvider.getCheckout(approvedCheckout),
                testDataProvider.getAccessToken());

        assertThat(refund.getStatus(), is(RefundStatus.PENDING));
        assertThat(refund.getAmount(), is(refundRequest.getAmount()));
        assertThat(refund.getMerchantRefundReferenceNumber(), is(refundRequest.getMerchantRefundReferenceNumber()));
        assertThat(refund.getMerchantReconciliationReferenceNumber(),
                is(refundRequest.getMerchantReconciliationReferenceNumber()));
        assertThat(refund.getNote(), is(refundRequest.getNote()));
    }

}
