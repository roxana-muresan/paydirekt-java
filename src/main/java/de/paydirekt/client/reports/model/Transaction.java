package de.paydirekt.client.reports.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A transaction, this represents a row in the report.
 */
public class Transaction {

    private final String correlationId;
    private final String endToEndReferenceNumber;
    private final String transactionTypeId;
    private final String transactionDate;
    private final String valueDate;
    private final String contractId;
    private final String merchantId;
    private final String merchantName;
    private final String merchantIban;
    private final String merchantReference;
    private final String merchantTransactionReferenceNumber;
    private final String merchantBankId;
    private final String buyerBankId;
    private final String buyerBankName;
    private final String transactionAmount;
    private final String transactionCurrency;
    private final String bankIntermediaryId;
    private final String bankIntermediaryName;
    private final String shopId;
    private final String shopName;
    private final String ageVerificationStatus;
    private final String paydirektExpress;
    private final String checkoutId;
    private final String checkoutInvoiceNr;
    private final String captureInvoiceNr;
    private final String paymentInformationId;
    private final String MCC;
    private final String DBI;
    private final String reconciliationReferenceNr;

    /**
     * Constructor.
     *
     * @param correlationId                      The correlation id of the related checkout.
     * @param endToEndReferenceNumber            Reference number of the checkout.
     * @param transactionTypeId                  Id of the transaction type.
     * @param transactionDate                    Date of the transaction.
     * @param valueDate                          Value date of the transaction.
     * @param contractId                         Id of the merchant contract.
     * @param merchantId                         Id of the merchant.
     * @param merchantName                       Name of the merchant.
     * @param merchantIban                       IBAN of the merchant.
     * @param merchantReference                  The reference of the merchant.
     * @param merchantTransactionReferenceNumber The transaction reference number of the merchant.
     * @param merchantBankId                     The bank id of the merchant.
     * @param buyerBankId                        The bank id of the buyer.
     * @param buyerBankName                      The name of the buyer's bank.
     * @param transactionAmount                  The amount of the transaction.
     * @param transactionCurrency                The currency of the transaction.
     * @param bankIntermediaryId                 The id of the buyer's bank intermediary
     * @param bankIntermediaryName               The name of the buyer's bank intermediary.
     * @param shopId                             The id of the shop.
     * @param shopName                           The name of the shop.
     * @param ageVerificationStatus              Result of the age verification.
     * @param paydirektExpress                   Whether this was an express checkout.
     * @param checkoutId                         The id of the checkout.
     * @param checkoutInvoiceNr                  The invoice number of the checkout.
     * @param captureInvoiceNr                   The invoice number of the capture.
     * @param paymentInformationId               The payment information id of the SEPA-Transaction.
     * @param MCC                                Merchant Category Code.
     * @param DBI                                Details Business Indicator.
     * @param reconciliationReferenceNr          The reference number which is used in the purpose description.
     */
    public Transaction(@JsonProperty("correlationId") String correlationId,
                       @JsonProperty("endToEndReferenceNumber") String endToEndReferenceNumber,
                       @JsonProperty("transactionTypeId") String transactionTypeId,
                       @JsonProperty("transactionDate") String transactionDate,
                       @JsonProperty("valueDate") String valueDate,
                       @JsonProperty("contractId") String contractId,
                       @JsonProperty("merchantId") String merchantId,
                       @JsonProperty("merchantName") String merchantName,
                       @JsonProperty("merchantIban") String merchantIban,
                       @JsonProperty("merchantReference") String merchantReference,
                       @JsonProperty("merchantTransactionReferenceNumber") String merchantTransactionReferenceNumber,
                       @JsonProperty("merchantBankId") String merchantBankId,
                       @JsonProperty("buyerBankId") String buyerBankId,
                       @JsonProperty("buyerBankName") String buyerBankName,
                       @JsonProperty("transactionAmount") String transactionAmount,
                       @JsonProperty("transactionCurrency") String transactionCurrency,
                       @JsonProperty("bankIntermediaryId") String bankIntermediaryId,
                       @JsonProperty("bankIntermediaryName") String bankIntermediaryName,
                       @JsonProperty("shopId") String shopId,
                       @JsonProperty("shopName") String shopName,
                       @JsonProperty("ageVerificationStatus") String ageVerificationStatus,
                       @JsonProperty("paydirektExpress") String paydirektExpress,
                       @JsonProperty("checkoutId") String checkoutId,
                       @JsonProperty("checkoutInvoiceNr") String checkoutInvoiceNr,
                       @JsonProperty("captureInvoiceNr") String captureInvoiceNr,
                       @JsonProperty("paymentInformationId") String paymentInformationId,
                       @JsonProperty("MCC") String MCC,
                       @JsonProperty("DBI") String DBI,
                       @JsonProperty("reconciliationReferenceNr") String reconciliationReferenceNr) {
        this.correlationId = correlationId;
        this.endToEndReferenceNumber = endToEndReferenceNumber;
        this.transactionTypeId = transactionTypeId;
        this.transactionDate = transactionDate;
        this.valueDate = valueDate;
        this.contractId = contractId;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantIban = merchantIban;
        this.merchantReference = merchantReference;
        this.merchantTransactionReferenceNumber = merchantTransactionReferenceNumber;
        this.merchantBankId = merchantBankId;
        this.buyerBankId = buyerBankId;
        this.buyerBankName = buyerBankName;
        this.transactionAmount = transactionAmount;
        this.transactionCurrency = transactionCurrency;
        this.bankIntermediaryId = bankIntermediaryId;
        this.bankIntermediaryName = bankIntermediaryName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.ageVerificationStatus = ageVerificationStatus;
        this.paydirektExpress = paydirektExpress;
        this.checkoutId = checkoutId;
        this.checkoutInvoiceNr = checkoutInvoiceNr;
        this.captureInvoiceNr = captureInvoiceNr;
        this.paymentInformationId = paymentInformationId;
        this.MCC = MCC;
        this.DBI = DBI;
        this.reconciliationReferenceNr = reconciliationReferenceNr;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getEndToEndReferenceNumber() {
        return endToEndReferenceNumber;
    }

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getValueDate() {
        return valueDate;
    }

    public String getContractId() {
        return contractId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantIban() {
        return merchantIban;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public String getMerchantTransactionReferenceNumber() {
        return merchantTransactionReferenceNumber;
    }

    public String getMerchantBankId() {
        return merchantBankId;
    }

    public String getBuyerBankId() {
        return buyerBankId;
    }

    public String getBuyerBankName() {
        return buyerBankName;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public String getBankIntermediaryId() {
        return bankIntermediaryId;
    }

    public String getBankIntermediaryName() {
        return bankIntermediaryName;
    }

    public String getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getAgeVerificationStatus() {
        return ageVerificationStatus;
    }

    public String getPaydirektExpress() {
        return paydirektExpress;
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public String getCheckoutInvoiceNr() {
        return checkoutInvoiceNr;
    }

    public String getCaptureInvoiceNr() {
        return captureInvoiceNr;
    }

    public String getPaymentInformationId() {
        return paymentInformationId;
    }

    public String getMCC() {
        return MCC;
    }

    public String getDBI() {
        return DBI;
    }

    public String getReconciliationReferenceNr() {
        return reconciliationReferenceNr;
    }
}
