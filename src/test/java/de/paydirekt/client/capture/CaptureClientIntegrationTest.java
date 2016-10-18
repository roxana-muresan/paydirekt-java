package de.paydirekt.client.capture;

import de.paydirekt.client.capture.model.Capture;
import de.paydirekt.client.capture.model.CaptureRequest;
import de.paydirekt.client.capture.model.CaptureStatus;
import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.testutil.TestDataProvider;
import org.junit.Before;
import org.junit.Test;

import static de.paydirekt.client.testutil.ClientsFactory.newCaptureClient;
import static de.paydirekt.client.testutil.MocksFactory.newCaptureRequest;
import static de.paydirekt.client.testutil.MocksFactory.newOrderCheckoutRequest;
import static de.paydirekt.client.testutil.TestDataProvider.newTestDataProvider;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the functionality of the {@link CaptureClient}
 */
public class CaptureClientIntegrationTest {

    private CaptureClient captureClient;
    private TestDataProvider testDataProvider;
    private Checkout approvedCheckout;

    @Before
    public void setUp() {
        captureClient = newCaptureClient();
        testDataProvider = newTestDataProvider();
        //An approved checkout is a precondition for the creation of a capture
        approvedCheckout = testDataProvider.getApprovedCheckout(newOrderCheckoutRequest());
    }

    @Test
    public void shouldCreateValidCapture() {
        CaptureRequest captureRequest = newCaptureRequest();

        Capture capture = captureClient.createCapture(
                captureRequest,
                approvedCheckout,
                testDataProvider.getAccessToken());

        assertThat(capture.getStatus(), is(CaptureStatus.SUCCESSFUL));
        assertThat(capture.getAmount(), is(captureRequest.getAmount()));
        assertThat(capture.getMerchantCaptureReferenceNumber(), is(captureRequest.getMerchantCaptureReferenceNumber()));
        assertThat(capture.getCaptureInvoiceReferenceNumber(), is(captureRequest.getCaptureInvoiceReferenceNumber()));
        assertThat(capture.getMerchantReconciliationReferenceNumber(),
                is(captureRequest.getMerchantReconciliationReferenceNumber()));
        assertThat(capture.getFinalCapture(), is(captureRequest.getFinalCapture()));
        assertThat(capture.getCallbackUrlStatusUpdates(), is(captureRequest.getCallbackUrlStatusUpdates()));
    }

    @Test
    public void retrievedCaptureShouldEqualCreatedCapture() {
        CaptureRequest captureRequest = newCaptureRequest();

        Capture createdCapture = captureClient.createCapture(
                captureRequest, testDataProvider.getCheckout(approvedCheckout),
                testDataProvider.getAccessToken());
        Capture retrievedCapture = captureClient.getCapture(createdCapture.getSelfLink(),
                testDataProvider.getAccessToken());

        assertThat(createdCapture, is(retrievedCapture));
    }

}
