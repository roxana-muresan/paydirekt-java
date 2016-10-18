package de.paydirekt.client.checkout.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.paydirekt.client.rest.HalResource;
import de.paydirekt.client.rest.Link;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

/**
 * A checkout represents the payment process.
 */
public class Checkout extends HalResource {

    private final String checkoutId;
    private final CheckoutType type;
    private final Boolean express;
    private final CheckoutStatus status;
    private final String creationTimestamp;
    private final BigDecimal totalAmount;
    private final BigDecimal shippingAmount;
    private final List<Item> items;
    private final ShoppingCartType shoppingCartType;
    private final DeliveryType deliveryType;
    private final BigDecimal orderAmount;
    private final Currency currency;
    private final Boolean overcapture;
    private final BigDecimal maxCapturableAmount;
    private final BigDecimal maxOvercaptureDifference;
    private final String correlationId;
    private final String note;
    private final ShippingAddress shippingAddress;
    private final ShippingAddress billingAddress;
    private final String merchantCustomerNumber;
    private final String merchantOrderReferenceNumber;
    private final String merchantInvoiceReferenceNumber;
    private final String merchantReconciliationReferenceNumber;
    private final Integer minimumAge;
    private final String redirectUrlAfterSuccess;
    private final String redirectUrlAfterCancellation;
    private final String redirectUrlAfterAgeVerificationFailure;
    private final String redirectUrlAfterRejection;
    private final String callbackUrlStatusUpdates;
    private final String callbackUrlCheckDestinations;
    private final String webUrlShippingTerms;

    /**
     * Constructor.
     *
     * @param checkoutId                             The id of this checkout.
     * @param type                                   The type of this checkout.
     * @param express                                Whether this is an express checkout.
     * @param status                                 The status of the checkout.
     * @param creationTimestamp                      The timestamp of the creation of the checkout.
     * @param totalAmount                            The total amount of the checkout, including all shipping costs.
     * @param shippingAmount                         The shipping amount of the checkout.
     * @param items                                  The included items.
     * @param shoppingCartType                       The type of shopping cart.
     * @param deliveryType                           The type of the delivery.
     * @param orderAmount                            The amount of the order excluding shipping costs.
     * @param currency                               The currency of the amounts.
     * @param overcapture                            Flag for activation of overcapture checkouts.
     * @param maxCapturableAmount                    The maximally capturable amount.
     * @param maxOvercaptureDifference               The difference between the totalAmount and maxCapturableAmount.
     * @param correlationId                          The id of the checkout and the related transactions.
     * @param note                                   A comment from the buyer.
     * @param shippingAddress                        The shipping address of the buyer.
     * @param billingAddress                         The billing address of the buyer.
     * @param merchantCustomerNumber                 Internal number of the customer, provided by the merchant
     * @param merchantOrderReferenceNumber           Internal number of the order, provided by the merchant
     * @param merchantInvoiceReferenceNumber         Internal unique invoice reference number, provided by the merchant.
     * @param merchantReconciliationReferenceNumber  Internal reconciliation number, provided by the merchant.
     * @param minimumAge                             The minimum age in years of the buyer required for this checkout.
     * @param redirectUrlAfterSuccess                The URL of the webshop to redirect to if the payment succeeds.
     * @param redirectUrlAfterCancellation           The URL of the webshop to redirect to if the payment is cancelled.
     * @param redirectUrlAfterAgeVerificationFailure The URL of the webshop to redirect to if age verification fails.
     * @param redirectUrlAfterRejection              The URL of the webshop to redirect to if the payment is rejected.
     * @param callbackUrlStatusUpdates               Callback URL of a endpoint that listens for changes in status.
     * @param callbackUrlCheckDestinations           Callback URL of a endpoint that checks which shipping and billing addresses are valid for a this checkout.
     * @param webUrlShippingTerms                    Link to the shipping terms of the merchant.
     * @param embedded                               Embedded resources.
     * @param links                                  Links to resources.
     */
    public Checkout(@JsonProperty("checkoutId") String checkoutId,
                    @JsonProperty("type") CheckoutType type,
                    @JsonProperty("express") Boolean express,
                    @JsonProperty("status") CheckoutStatus status,
                    @JsonProperty("creationTimestamp") String creationTimestamp,
                    @JsonProperty("totalAmount") BigDecimal totalAmount,
                    @JsonProperty("shippingAmount") BigDecimal shippingAmount,
                    @JsonProperty("items") List<Item> items,
                    @JsonProperty("shoppingCartType") ShoppingCartType shoppingCartType,
                    @JsonProperty("deliveryType") DeliveryType deliveryType,
                    @JsonProperty("orderAmount") BigDecimal orderAmount,
                    @JsonProperty("currency") Currency currency,
                    @JsonProperty("overcapture") Boolean overcapture,
                    @JsonProperty("maxCapturableAmount") BigDecimal maxCapturableAmount,
                    @JsonProperty("maxOvercaptureDifference") BigDecimal maxOvercaptureDifference,
                    @JsonProperty("correlationId") String correlationId,
                    @JsonProperty("note") String note,
                    @JsonProperty("shippingAddress") ShippingAddress shippingAddress,
                    @JsonProperty("billingAddress") ShippingAddress billingAddress,
                    @JsonProperty("merchantCustomerNumber") String merchantCustomerNumber,
                    @JsonProperty("merchantOrderReferenceNumber") String merchantOrderReferenceNumber,
                    @JsonProperty("merchantInvoiceReferenceNumber") String merchantInvoiceReferenceNumber,
                    @JsonProperty("merchantReconciliationReferenceNumber") String merchantReconciliationReferenceNumber,
                    @JsonProperty("minimumAge") Integer minimumAge,
                    @JsonProperty("redirectUrlAfterSuccess") String redirectUrlAfterSuccess,
                    @JsonProperty("redirectUrlAfterCancellation") String redirectUrlAfterCancellation,
                    @JsonProperty("redirectUrlAfterAgeVerificationFailure") String redirectUrlAfterAgeVerificationFailure,
                    @JsonProperty("redirectUrlAfterRejection") String redirectUrlAfterRejection,
                    @JsonProperty("callbackUrlStatusUpdates") String callbackUrlStatusUpdates,
                    @JsonProperty("callbackUrlCheckDestinations") String callbackUrlCheckDestinations,
                    @JsonProperty("webUrlShippingTerms") String webUrlShippingTerms,
                    @JsonProperty("_embedded") Map<String, Object> embedded,
                    @JsonProperty("_links") Map<String, Link> links) {
        super(embedded, links);
        this.checkoutId = checkoutId;
        this.type = type;
        this.express = express;
        this.status = status;
        this.creationTimestamp = creationTimestamp;
        this.totalAmount = totalAmount;
        this.shippingAmount = shippingAmount;
        this.items = unmodifiableList(items);
        this.shoppingCartType = shoppingCartType;
        this.deliveryType = deliveryType;
        this.orderAmount = orderAmount;
        this.currency = currency;
        this.overcapture = overcapture;
        this.maxCapturableAmount = maxCapturableAmount;
        this.maxOvercaptureDifference = maxOvercaptureDifference;
        this.correlationId = correlationId;
        this.note = note;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.merchantCustomerNumber = merchantCustomerNumber;
        this.merchantOrderReferenceNumber = merchantOrderReferenceNumber;
        this.merchantInvoiceReferenceNumber = merchantInvoiceReferenceNumber;
        this.merchantReconciliationReferenceNumber = merchantReconciliationReferenceNumber;
        this.minimumAge = minimumAge;
        this.redirectUrlAfterSuccess = redirectUrlAfterSuccess;
        this.redirectUrlAfterCancellation = redirectUrlAfterCancellation;
        this.redirectUrlAfterAgeVerificationFailure = redirectUrlAfterAgeVerificationFailure;
        this.redirectUrlAfterRejection = redirectUrlAfterRejection;
        this.callbackUrlStatusUpdates = callbackUrlStatusUpdates;
        this.callbackUrlCheckDestinations = callbackUrlCheckDestinations;
        this.webUrlShippingTerms = webUrlShippingTerms;
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public CheckoutType getType() {
        return type;
    }

    public Boolean isExpress() {
        return express;
    }

    public CheckoutStatus getStatus() {
        return status;
    }

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Boolean isOvercapture() {
        return overcapture;
    }

    public BigDecimal getMaxCapturableAmount() {
        return maxCapturableAmount;
    }

    public BigDecimal getMaxOvercaptureDifference() {
        return maxOvercaptureDifference;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getNote() {
        return note;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public ShippingAddress getBillingAddress() {
        return billingAddress;
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

    public String getMerchantReconciliationReferenceNumber() {
        return merchantReconciliationReferenceNumber;
    }

    public Integer getMinimumAge() {
        return minimumAge;
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

    public String getRedirectUrlAfterRejection() {
        return redirectUrlAfterRejection;
    }

    public String getCallbackUrlStatusUpdates() {
        return callbackUrlStatusUpdates;
    }

    public String getCallbackUrlCheckDestinations() {
        return callbackUrlCheckDestinations;
    }

    public String getWebUrlShippingTerms() {
        return webUrlShippingTerms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkoutId, type, express, status, creationTimestamp, totalAmount, shippingAmount, items, shoppingCartType, deliveryType, orderAmount, currency, overcapture, maxCapturableAmount, maxOvercaptureDifference, correlationId, note, shippingAddress, billingAddress, merchantCustomerNumber, merchantOrderReferenceNumber, merchantInvoiceReferenceNumber, merchantReconciliationReferenceNumber, minimumAge, redirectUrlAfterSuccess, redirectUrlAfterCancellation, redirectUrlAfterAgeVerificationFailure, redirectUrlAfterRejection, callbackUrlStatusUpdates, callbackUrlCheckDestinations, webUrlShippingTerms);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Checkout)) {
            return false;
        }
        final Checkout other = (Checkout) obj;
        return Objects.equals(checkoutId, other.checkoutId)
                && Objects.equals(type, other.type)
                && Objects.equals(express, other.express)
                && Objects.equals(status, other.status)
                && Objects.equals(creationTimestamp, other.creationTimestamp)
                && Objects.equals(totalAmount, other.totalAmount)
                && Objects.equals(shippingAmount, other.shippingAmount)
                && Objects.equals(items, other.items)
                && Objects.equals(shoppingCartType, other.shoppingCartType)
                && Objects.equals(deliveryType, other.deliveryType)
                && Objects.equals(orderAmount, other.orderAmount)
                && Objects.equals(currency, other.currency)
                && Objects.equals(overcapture, other.overcapture)
                && Objects.equals(maxCapturableAmount, other.maxCapturableAmount)
                && Objects.equals(maxOvercaptureDifference, other.maxOvercaptureDifference)
                && Objects.equals(correlationId, other.correlationId)
                && Objects.equals(note, other.note)
                && Objects.equals(shippingAddress, other.shippingAddress)
                && Objects.equals(billingAddress, other.billingAddress)
                && Objects.equals(merchantCustomerNumber, other.merchantCustomerNumber)
                && Objects.equals(merchantOrderReferenceNumber, other.merchantOrderReferenceNumber)
                && Objects.equals(merchantInvoiceReferenceNumber, other.merchantInvoiceReferenceNumber)
                && Objects.equals(merchantReconciliationReferenceNumber, other.merchantReconciliationReferenceNumber)
                && Objects.equals(minimumAge, other.minimumAge)
                && Objects.equals(redirectUrlAfterSuccess, other.redirectUrlAfterSuccess)
                && Objects.equals(redirectUrlAfterCancellation, other.redirectUrlAfterCancellation)
                && Objects.equals(redirectUrlAfterAgeVerificationFailure, other.redirectUrlAfterAgeVerificationFailure)
                && Objects.equals(redirectUrlAfterRejection, other.redirectUrlAfterRejection)
                && Objects.equals(callbackUrlStatusUpdates, other.callbackUrlStatusUpdates)
                && Objects.equals(callbackUrlCheckDestinations, other.callbackUrlCheckDestinations)
                && Objects.equals(webUrlShippingTerms, other.webUrlShippingTerms);
    }
}
