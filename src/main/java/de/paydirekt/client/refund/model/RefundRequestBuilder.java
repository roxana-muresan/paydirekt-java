package de.paydirekt.client.refund.model;

import java.math.BigDecimal;

/**
 * Builder for a {@link Refund}.
 */
public final class RefundRequestBuilder {

    private BigDecimal amount;
    private String note;
    private String merchantRefundReferenceNumber;
    private String merchantReconciliationReferenceNumber;

    private RefundRequestBuilder() {
    }

    public static RefundRequestBuilder aRefundRequest() {
        return new RefundRequestBuilder();
    }

    public RefundRequestBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public RefundRequestBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public RefundRequestBuilder withMerchantRefundReferenceNumber(String merchantRefundReferenceNumber) {
        this.merchantRefundReferenceNumber = merchantRefundReferenceNumber;
        return this;
    }

    public RefundRequestBuilder withMerchantReconciliationReferenceNumber(String merchantReconciliationReferenceNumber) {
        this.merchantReconciliationReferenceNumber = merchantReconciliationReferenceNumber;
        return this;
    }

    public RefundRequest build() {
        RefundRequest refundRequest = new RefundRequest(
                amount,
                note,
                merchantRefundReferenceNumber,
                merchantReconciliationReferenceNumber);
        return refundRequest;
    }
}
