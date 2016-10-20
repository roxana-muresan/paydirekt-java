package de.paydirekt.client.capture.model;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Represents the request body for the creation of a {@link Capture}.
 */
public class CaptureRequest {

    private final BigDecimal amount;
    private final Boolean finalCapture;
    private final String note;
    private final String merchantCaptureReferenceNumber;
    private final String merchantReconciliationReferenceNumber;
    private final String captureInvoiceReferenceNumber;
    private final String callbackUrlStatusUpdates;

    /**
     * Constructor.
     *
     * @param amount                                The value of the order. (Mandatory)
     * @param finalCapture                          Whether this is the final capture of a checkout. (Optional)
     * @param note                                  Comments from the buyer. (Optional)
     * @param merchantCaptureReferenceNumber        Internal reference number, provided by the merchant. (Optional)
     * @param merchantReconciliationReferenceNumber Internal reconciliation number, provided by the merchant. (Optional)
     * @param captureInvoiceReferenceNumber         Internal unique invoice reference number, provided by the merchant. (Optional)
     * @param callbackUrlStatusUpdates              Callback URL of a endpoint that listens for changes in status. (Optional)
     */
    public CaptureRequest(BigDecimal amount,
                          Boolean finalCapture,
                          String note,
                          String merchantCaptureReferenceNumber,
                          String merchantReconciliationReferenceNumber,
                          String captureInvoiceReferenceNumber,
                          String callbackUrlStatusUpdates) {
        requireNonNull(amount);

        this.amount = amount;
        this.finalCapture = finalCapture;
        this.note = note;
        this.merchantCaptureReferenceNumber = merchantCaptureReferenceNumber;
        this.merchantReconciliationReferenceNumber = merchantReconciliationReferenceNumber;
        this.captureInvoiceReferenceNumber = captureInvoiceReferenceNumber;
        this.callbackUrlStatusUpdates = callbackUrlStatusUpdates;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Boolean getFinalCapture() {
        return finalCapture;
    }

    public String getNote() {
        return note;
    }

    public String getMerchantCaptureReferenceNumber() {
        return merchantCaptureReferenceNumber;
    }

    public String getMerchantReconciliationReferenceNumber() {
        return merchantReconciliationReferenceNumber;
    }

    public String getCaptureInvoiceReferenceNumber() {
        return captureInvoiceReferenceNumber;
    }

    public String getCallbackUrlStatusUpdates() {
        return callbackUrlStatusUpdates;
    }
}
