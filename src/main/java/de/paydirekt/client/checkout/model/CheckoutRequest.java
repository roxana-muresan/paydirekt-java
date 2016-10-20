package de.paydirekt.client.checkout.model;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

/**
 * Represents the request body for the creation of a {@link Checkout}.
 */
public class CheckoutRequest {

    private final CheckoutType type;
    private final BigDecimal totalAmount;
    private final BigDecimal shippingAmount;
    private final BigDecimal orderAmount;
    private final Boolean overcapture;
    private final Currency currency;
    private final List<Item> items;
    private final ShoppingCartType shoppingCartType;
    private final DeliveryType deliveryType;
    private final ShippingAddress shippingAddress;
    private final String merchantCustomerNumber;
    private final String merchantOrderReferenceNumber;
    private final String merchantInvoiceReferenceNumber;
    private final String sha256hashedEmailAddress;
    private final String redirectUrlAfterSuccess;
    private final String redirectUrlAfterCancellation;
    private final String redirectUrlAfterAgeVerificationFailure;
    private final String callbackUrlStatusUpdates;
    private final Integer minimumAge;
    private final String redirectUrlAfterRejection;
    private final String note;
    private final Boolean express;
    private final String callbackUrlCheckDestinations;
    private final String webUrlShippingTerms;
    private final String merchantReconciliationReferenceNumber;

    /**
     * @param type                                   The type of this checkout. (Mandatory)
     * @param totalAmount                            The total amount of the checkout, including all shipping costs. (Mandatory)
     * @param shippingAmount                         The shipping amount of the checkout. (Optional)
     * @param orderAmount                            The amount of the order excluding shipping costs. (Optional)
     * @param overcapture                            Flag for activation of overcapture checkouts. (Optional)
     * @param currency                               The currency of the amounts. (Mandatory)
     * @param items                                  The included items. (Optional)
     * @param shoppingCartType                       The type of shopping cart. (Optional)
     * @param deliveryType                           The type of the delivery. (Optional)
     * @param shippingAddress                        The shipping address of the buyer. (Mandatory if not express checkout)
     * @param merchantCustomerNumber                 Internal number of the customer, provided by the merchant. (Optional)
     * @param merchantOrderReferenceNumber           Internal number of the order, provided by the merchant. (Mandatory)
     * @param merchantInvoiceReferenceNumber         Internal unique invoice reference number, provided by the merchant. (Optional)
     * @param sha256hashedEmailAddress               The hashed email address of the buyer. (Optional)
     * @param redirectUrlAfterSuccess                The URL of the webshop to redirect to if the payment succeeds. (Mandatory)
     * @param redirectUrlAfterCancellation           The URL of the webshop to redirect to if the payment is cancelled. (Mandatory)
     * @param redirectUrlAfterAgeVerificationFailure The URL of the webshop to redirect to if age verification fails. (Mandatory if minAge is set)
     * @param callbackUrlStatusUpdates               Callback URL of a endpoint that listens for changes in status. (Optional)
     * @param minimumAge                             The minimum age in years of the buyer required for this checkout. (Optional)
     * @param redirectUrlAfterRejection              The URL of the webshop to redirect to if the payment is rejected. (Mandatory)
     * @param note                                   A comment from the buyer. (Optional)
     * @param express                                Whether this is an express checkout. (Optional)
     * @param callbackUrlCheckDestinations           Callback URL of a endpoint that checks which shipping and billing addresses are valid for a this checkout. (Mandatory if express checkout)
     * @param webUrlShippingTerms                    Link to the shipping terms of the merchant. (Mandatory if express checkout)
     * @param merchantReconciliationReferenceNumber  Internal reconciliation number, provided by the merchant. (Optional)
     */
    public CheckoutRequest(CheckoutType type,
                           BigDecimal totalAmount,
                           BigDecimal shippingAmount,
                           BigDecimal orderAmount,
                           Boolean overcapture,
                           Currency currency,
                           List<Item> items,
                           ShoppingCartType shoppingCartType,
                           DeliveryType deliveryType,
                           ShippingAddress shippingAddress,
                           String merchantCustomerNumber,
                           String merchantOrderReferenceNumber,
                           String merchantInvoiceReferenceNumber,
                           String sha256hashedEmailAddress,
                           String redirectUrlAfterSuccess,
                           String redirectUrlAfterCancellation,
                           String redirectUrlAfterAgeVerificationFailure,
                           String callbackUrlStatusUpdates,
                           Integer minimumAge,
                           String redirectUrlAfterRejection,
                           String note,
                           Boolean express,
                           String callbackUrlCheckDestinations,
                           String webUrlShippingTerms,
                           String merchantReconciliationReferenceNumber) {

        requireNonNull(type);
        requireNonNull(totalAmount);
        requireNonNull(currency);
        requireNonNull(merchantOrderReferenceNumber);
        requireNonNull(redirectUrlAfterCancellation);
        requireNonNull(redirectUrlAfterRejection);
        requireNonNull(redirectUrlAfterSuccess);

        if (minimumAge != null) {
            requireNonNull(redirectUrlAfterAgeVerificationFailure);
        }

        if (Boolean.TRUE.equals(express)) {
            requireNonNull(webUrlShippingTerms);
            requireNonNull(callbackUrlCheckDestinations);
        } else {
            requireNonNull(shippingAddress);
            if (shoppingCartType == ShoppingCartType.DIGITAL) {
                requireNonNull(shippingAddress.getEmailAddress());
            } else {
                requireNonNull(shippingAddress.getCity());
                requireNonNull(shippingAddress.getCountryCode());
                requireNonNull(shippingAddress.getZip());
            }
        }

        this.type = type;
        this.totalAmount = totalAmount;
        this.shippingAmount = shippingAmount;
        this.orderAmount = orderAmount;
        this.overcapture = overcapture;
        this.currency = currency;
        this.items = unmodifiableList(items);
        this.shoppingCartType = shoppingCartType;
        this.deliveryType = deliveryType;
        this.shippingAddress = shippingAddress;
        this.merchantCustomerNumber = merchantCustomerNumber;
        this.merchantOrderReferenceNumber = merchantOrderReferenceNumber;
        this.merchantInvoiceReferenceNumber = merchantInvoiceReferenceNumber;
        this.sha256hashedEmailAddress = sha256hashedEmailAddress;
        this.redirectUrlAfterSuccess = redirectUrlAfterSuccess;
        this.redirectUrlAfterCancellation = redirectUrlAfterCancellation;
        this.redirectUrlAfterAgeVerificationFailure = redirectUrlAfterAgeVerificationFailure;
        this.callbackUrlStatusUpdates = callbackUrlStatusUpdates;
        this.minimumAge = minimumAge;
        this.redirectUrlAfterRejection = redirectUrlAfterRejection;
        this.note = note;
        this.express = express;
        this.callbackUrlCheckDestinations = callbackUrlCheckDestinations;
        this.webUrlShippingTerms = webUrlShippingTerms;
        this.merchantReconciliationReferenceNumber = merchantReconciliationReferenceNumber;
    }

    public CheckoutType getType() {
        return type;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public Boolean isOvercapture() {
        return overcapture;
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<Item> getItems() {
        return items;
    }

    public ShoppingCartType getShoppingCartType() {
        return shoppingCartType;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public String getMerchantCustomerNumber() {
        return merchantCustomerNumber;
    }

    public String getMerchantOrderReferenceNumber() {
        return merchantOrderReferenceNumber;
    }

    public String getMerchantInvoiceReferenceNumber() {
        return merchantInvoiceReferenceNumber;
    }

    public String getSha256hashedEmailAddress() {
        return sha256hashedEmailAddress;
    }

    public String getRedirectUrlAfterSuccess() {
        return redirectUrlAfterSuccess;
    }

    public String getRedirectUrlAfterCancellation() {
        return redirectUrlAfterCancellation;
    }

    public String getRedirectUrlAfterAgeVerificationFailure() {
        return redirectUrlAfterAgeVerificationFailure;
    }

    public String getCallbackUrlStatusUpdates() {
        return callbackUrlStatusUpdates;
    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public String getRedirectUrlAfterRejection() {
        return redirectUrlAfterRejection;
    }

    public String getNote() {
        return note;
    }

    public Boolean isExpress() {
        return express;
    }

    public String getCallbackUrlCheckDestinations() {
        return callbackUrlCheckDestinations;
    }

    public String getWebUrlShippingTerms() {
        return webUrlShippingTerms;
    }

    public String getMerchantReconciliationReferenceNumber() {
        return merchantReconciliationReferenceNumber;
    }
}
