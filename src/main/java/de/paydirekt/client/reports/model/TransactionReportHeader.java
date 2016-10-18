package de.paydirekt.client.reports.model;

import java.util.Arrays;

/**
 * The headers of a CSV-Report on transactions
 */
public enum TransactionReportHeader {

    AGE_VERIFICATION_STATUS("ageVerificationStatus"),
    BANK_INTERMEDIARY_ID("bankIntermediaryId"),
    BANK_INTERMEDIARY_NAME("bankIntermediaryName"),
    BUYER_BANK_ID("buyerBankId"),
    BUYER_BANK_NAME("buyerBankName"),
    CORRELATION_ID("correlationId"),
    CONTRACT_ID("contractId"),
    CHECKOUT_ID("checkoutId"),
    CHECKOUT_INVOICE_NR("checkoutInvoiceNr"),
    CAPTURE_INVOICE_NR("captureInvoiceNr"),
    DBI("DBI"),
    END_TO_END_REFERENCE_NUMBER("endToEndReferenceNumber"),
    MERCHANT_ID("merchantId"),
    MERCHANT_NAME("merchantName"),
    MERCHANT_IBAN("merchantIban"),
    MERCHANT_REFERENCE("merchantReference"),
    MERCHANT_TRANSACTION_REFERENCE_NUMBER("merchantTransactionReferenceNumber"),
    MERCHANT_BANK_ID("merchantBankId"),
    MCC("MCC"),
    PAYDIREKT_EXPRESS("paydirektExpress"),
    PAYMENT_INFORMATION_ID("paymentInformationId"),
    SHOP_ID("shopId"),
    SHOP_NAME("shopName"),
    TRANSACTION_TPE_ID("transactionTypeId"),
    TRANSACTION_DATE("transactionDate"),
    TRANSACTION_AMOUNT("transactionAmount"),
    TRANSACTION_CURRENCY("transactionCurrency"),
    VALUE_DATE("valueDate");

    private String header;

    /**
     * Constructor.
     *
     * @param header The value of the header.
     */
    TransactionReportHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    /**
     * Get all header values.
     *
     * @return All header values.
     */
    public static String[] getHeaders() {
        return Arrays.stream(values())
                .map(TransactionReportHeader::getHeader)
                .toArray(size -> new String[size]);
    }

}
