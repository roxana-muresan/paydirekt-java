package de.paydirekt.client.checkout.model;

import de.paydirekt.client.common.Sha256Encoder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Builder for a {@link CheckoutRequest}.
 */
public final class CheckoutRequestBuilder {

    private CheckoutType type;
    private BigDecimal totalAmount;
    private BigDecimal shippingAmount;
    private BigDecimal orderAmount;
    private Boolean overcapture;
    private Currency currency;
    private List<Item> items;
    private ShoppingCartType shoppingCartType;
    private DeliveryType deliveryType;
    private ShippingAddress shippingAddress;
    private String merchantCustomerNumber;
    private String merchantOrderReferenceNumber;
    private String merchantInvoiceReferenceNumber;
    private String sha256hashedEmailAddress;
    private String redirectUrlAfterSuccess;
    private String redirectUrlAfterCancellation;
    private String redirectUrlAfterAgeVerificationFailure;
    private String callbackUrlStatusUpdates;
    private Integer minimumAge;
    private String redirectUrlAfterRejection;
    private String note;
    private Boolean express;
    private String callbackUrlCheckDestinations;
    private String webUrlShippingTerms;
    private String merchantReconciliationReferenceNumber;

    private CheckoutRequestBuilder() {
    }

    public static CheckoutRequestBuilder aCheckoutRequest() {
        return new CheckoutRequestBuilder();
    }

    public CheckoutRequestBuilder withType(CheckoutType type) {
        this.type = type;
        return this;
    }

    public CheckoutRequestBuilder withTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public CheckoutRequestBuilder withShippingAmount(BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
        return this;
    }

    public CheckoutRequestBuilder withOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }

    public CheckoutRequestBuilder withOvercapture(Boolean overcapture) {
        this.overcapture = overcapture;
        return this;
    }

    public CheckoutRequestBuilder withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public CheckoutRequestBuilder withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public CheckoutRequestBuilder withShoppingCartType(ShoppingCartType shoppingCartType) {
        this.shoppingCartType = shoppingCartType;
        return this;
    }

    public CheckoutRequestBuilder withDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    public CheckoutRequestBuilder withShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public CheckoutRequestBuilder withMerchantCustomerNumber(String merchantCustomerNumber) {
        this.merchantCustomerNumber = merchantCustomerNumber;
        return this;
    }

    public CheckoutRequestBuilder withMerchantOrderReferenceNumber(String merchantOrderReferenceNumber) {
        this.merchantOrderReferenceNumber = merchantOrderReferenceNumber;
        return this;
    }

    public CheckoutRequestBuilder withMerchantInvoiceReferenceNumber(String merchantInvoiceReferenceNumber) {
        this.merchantInvoiceReferenceNumber = merchantInvoiceReferenceNumber;
        return this;
    }

    public CheckoutRequestBuilder withSha256hashedEmailAddress(String sha256hashedEmailAddress) {
        this.sha256hashedEmailAddress = sha256hashedEmailAddress;
        return this;
    }

    /**
     * Encodes the given emailAddress via SHA-256 and sets the sha256hashedEmailAddress property.
     *
     * @param emailAddress The emailAddress
     * @return the builder.
     */
    public CheckoutRequestBuilder withEmailAddress(String emailAddress) {
        this.sha256hashedEmailAddress = Sha256Encoder.encodeToSha256(emailAddress);
        return this;
    }

    public CheckoutRequestBuilder withRedirectUrlAfterSuccess(String redirectUrlAfterSuccess) {
        this.redirectUrlAfterSuccess = redirectUrlAfterSuccess;
        return this;
    }

    public CheckoutRequestBuilder withRedirectUrlAfterCancellation(String redirectUrlAfterCancellation) {
        this.redirectUrlAfterCancellation = redirectUrlAfterCancellation;
        return this;
    }

    public CheckoutRequestBuilder withRedirectUrlAfterAgeVerificationFailure(String redirectUrlAfterAgeVerificationFailure) {
        this.redirectUrlAfterAgeVerificationFailure = redirectUrlAfterAgeVerificationFailure;
        return this;
    }

    public CheckoutRequestBuilder withCallbackUrlStatusUpdates(String callbackUrlStatusUpdates) {
        this.callbackUrlStatusUpdates = callbackUrlStatusUpdates;
        return this;
    }

    public CheckoutRequestBuilder withMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
        return this;
    }

    public CheckoutRequestBuilder withRedirectUrlAfterRejection(String redirectUrlAfterRejection) {
        this.redirectUrlAfterRejection = redirectUrlAfterRejection;
        return this;
    }

    public CheckoutRequestBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public CheckoutRequestBuilder withExpress(Boolean express) {
        this.express = express;
        return this;
    }

    public CheckoutRequestBuilder withCallbackUrlCheckDestinations(String callbackUrlCheckDestinations) {
        this.callbackUrlCheckDestinations = callbackUrlCheckDestinations;
        return this;
    }

    public CheckoutRequestBuilder withWebUrlShippingTerms(String webUrlShippingTerms) {
        this.webUrlShippingTerms = webUrlShippingTerms;
        return this;
    }

    public CheckoutRequestBuilder withMerchantReconciliationReferenceNumber(String merchantReconciliationReferenceNumber) {
        this.merchantReconciliationReferenceNumber = merchantReconciliationReferenceNumber;
        return this;
    }

    public CheckoutRequest build() {
        CheckoutRequest checkoutRequest = new CheckoutRequest(
                type,
                totalAmount,
                shippingAmount,
                orderAmount,
                overcapture,
                currency,
                items,
                shoppingCartType,
                deliveryType,
                shippingAddress,
                merchantCustomerNumber,
                merchantOrderReferenceNumber,
                merchantInvoiceReferenceNumber,
                sha256hashedEmailAddress,
                redirectUrlAfterSuccess,
                redirectUrlAfterCancellation,
                redirectUrlAfterAgeVerificationFailure,
                callbackUrlStatusUpdates,
                minimumAge,
                redirectUrlAfterRejection,
                note,
                express,
                callbackUrlCheckDestinations,
                webUrlShippingTerms,
                merchantReconciliationReferenceNumber);
        return checkoutRequest;
    }
}
