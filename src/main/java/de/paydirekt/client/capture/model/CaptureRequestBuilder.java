package de.paydirekt.client.capture.model;

import java.math.BigDecimal;

/**
 * Builder for a {@link CaptureRequest}.
 */
public final class CaptureRequestBuilder {

    private BigDecimal amount;
    private Boolean finalCapture;
    private String note;
    private String merchantCaptureReferenceNumber;
    private String merchantReconciliationReferenceNumber;
    private String captureInvoiceReferenceNumber;
    private String callbackUrlStatusUpdates;

    private CaptureRequestBuilder() {
    }

    public static CaptureRequestBuilder aCaptureRequest() {
        return new CaptureRequestBuilder();
    }

    public CaptureRequestBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public CaptureRequestBuilder withFinalCapture(Boolean finalCapture) {
        this.finalCapture = finalCapture;
        return this;
    }

    public CaptureRequestBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public CaptureRequestBuilder withMerchantCaptureReferenceNumber(String merchantCaptureReferenceNumber) {
        this.merchantCaptureReferenceNumber = merchantCaptureReferenceNumber;
        return this;
    }

    public CaptureRequestBuilder withMerchantReconciliationReferenceNumber(String merchantReconciliationReferenceNumber) {
        this.merchantReconciliationReferenceNumber = merchantReconciliationReferenceNumber;
        return this;
    }

    public CaptureRequestBuilder withCaptureInvoiceReferenceNumber(String captureInvoiceReferenceNumber) {
        this.captureInvoiceReferenceNumber = captureInvoiceReferenceNumber;
        return this;
    }

    public CaptureRequestBuilder withCallbackUrlStatusUpdates(String callbackUrlStatusUpdates) {
        this.callbackUrlStatusUpdates = callbackUrlStatusUpdates;
        return this;
    }

    public CaptureRequest build() {
        CaptureRequest captureRequest = new CaptureRequest(
                amount,
                finalCapture,
                note,
                merchantCaptureReferenceNumber,
                merchantReconciliationReferenceNumber,
                captureInvoiceReferenceNumber,
                callbackUrlStatusUpdates);
        return captureRequest;
    }
}
