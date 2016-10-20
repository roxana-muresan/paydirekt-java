package de.paydirekt.client.refund.model;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

/**
 * Represents the request body for the creation of a {@link Refund}.
 */
public class RefundRequest {

    private final BigDecimal amount;
    private final String note;
    private final String merchantRefundReferenceNumber;
    private final String merchantReconciliationReferenceNumber;

    /**
     * Constructor.
     *
     * @param amount                                The value of the refund. (Mandatory)
     * @param note                                  Comment for the buyer. (Optional)
     * @param merchantRefundReferenceNumber         Internal reference number, provided by the merchant. (Optional)
     * @param merchantReconciliationReferenceNumber Internal reconciliation number, provided by the merchant. (Optional)
     */
    public RefundRequest(BigDecimal amount,
                         String note,
                         String merchantRefundReferenceNumber,
                         String merchantReconciliationReferenceNumber) {
        nonNull(amount);

        this.amount = amount;
        this.note = note;
        this.merchantRefundReferenceNumber = merchantRefundReferenceNumber;
        this.merchantReconciliationReferenceNumber = merchantReconciliationReferenceNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getMerchantRefundReferenceNumber() {
        return merchantRefundReferenceNumber;
    }

    public String getMerchantReconciliationReferenceNumber() {
        return merchantReconciliationReferenceNumber;
    }
}
