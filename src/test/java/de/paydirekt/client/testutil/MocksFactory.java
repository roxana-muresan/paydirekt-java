package de.paydirekt.client.testutil;

import de.paydirekt.client.capture.model.CaptureRequest;
import de.paydirekt.client.capture.model.CaptureRequestBuilder;
import de.paydirekt.client.checkout.model.CheckoutRequest;
import de.paydirekt.client.checkout.model.CheckoutRequestBuilder;
import de.paydirekt.client.checkout.model.CheckoutType;
import de.paydirekt.client.checkout.model.Currency;
import de.paydirekt.client.checkout.model.Item;
import de.paydirekt.client.checkout.model.ItemBuilder;
import de.paydirekt.client.checkout.model.ShippingAddress;
import de.paydirekt.client.checkout.model.ShippingAddressBuilder;
import de.paydirekt.client.refund.model.RefundRequest;
import de.paydirekt.client.refund.model.RefundRequestBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Factory for the creation of mocks to be used in tests.
 */
public final class MocksFactory {

    public static RefundRequest newRefundRequest() {
        return RefundRequestBuilder.
                aRefundRequest()
                .withAmount(new BigDecimal("10"))
                .withNote("Thanks for shopping.")
                .withMerchantRefundReferenceNumber("merch-1234")
                .withMerchantReconciliationReferenceNumber("merch-567")
                .build();
    }

    public static CaptureRequest newCaptureRequest() {
        return CaptureRequestBuilder
                .aCaptureRequest()
                .withAmount(new BigDecimal("10"))
                .withNote("Thanks for shopping.")
                .withMerchantCaptureReferenceNumber("capture-21323")
                .withCaptureInvoiceReferenceNumber("invoice-21323")
                .build();
    }

    public static CheckoutRequest newOrderCheckoutRequest() {
        return CheckoutRequestBuilder
                .aCheckoutRequest()
                .withType(CheckoutType.ORDER)
                .withTotalAmount(new BigDecimal("100"))
                .withShippingAmount(new BigDecimal("3.5"))
                .withOrderAmount(new BigDecimal("96.5"))
                .withCurrency(Currency.EUR)
                .withMerchantCustomerNumber("cust-732477")
                .withMerchantInvoiceReferenceNumber("20150112334345")
                .withMerchantOrderReferenceNumber("order-A12223412")
                .withItems(newItems())
                .withShippingAddress(newAddress())
                .withNote("Ihr Einkauf bei Spielauto-Versand")
                .withEmailAddress("max@muster.de")
                .withMinimumAge(18)
                .withRedirectUrlAfterSuccess("https://spielauto-versand.de/order/123/success")
                .withRedirectUrlAfterRejection("https://spielauto-versand.de/order/123/rejection")
                .withRedirectUrlAfterCancellation("https://spielauto-versand.de/order/123/cancellation")
                .withRedirectUrlAfterAgeVerificationFailure("https://spielauto-versand.de/order/123/ageverificationfailed")
                .build();
    }

    public static CheckoutRequest newDirectSalesCheckoutRequest() {
        return CheckoutRequestBuilder
                .aCheckoutRequest()
                .withType(CheckoutType.DIRECT_SALE)
                .withTotalAmount(new BigDecimal("100"))
                .withShippingAmount(new BigDecimal("3.5"))
                .withOrderAmount(new BigDecimal("96.5"))
                .withCurrency(Currency.EUR)
                .withMerchantCustomerNumber("cust-732477")
                .withMerchantInvoiceReferenceNumber("20150112334345")
                .withMerchantOrderReferenceNumber("order-A12223412")
                .withItems(newItems())
                .withShippingAddress(newAddress())
                .withNote("Ihr Einkauf bei Spielauto-Versand")
                .withEmailAddress("max@muster.de")
                .withMinimumAge(18)
                .withRedirectUrlAfterSuccess("https://spielauto-versand.de/order/123/success")
                .withRedirectUrlAfterRejection("https://spielauto-versand.de/order/123/rejection")
                .withRedirectUrlAfterCancellation("https://spielauto-versand.de/order/123/cancellation")
                .withRedirectUrlAfterAgeVerificationFailure("https://spielauto-versand.de/order/123/ageverificationfailed")
                .build();
    }

    public static CheckoutRequest newExpressCheckoutRequest() {
        return CheckoutRequestBuilder
                .aCheckoutRequest()
                .withExpress(true)
                .withCallbackUrlCheckDestinations("https://spielauto-versand.de/destinations/check")
                .withWebUrlShippingTerms("https://spielauto-versand.de//shippingterms")
                .withType(CheckoutType.DIRECT_SALE)
                .withTotalAmount(new BigDecimal("100"))
                .withShippingAmount(new BigDecimal("3.5"))
                .withOrderAmount(new BigDecimal("96.5"))
                .withCurrency(Currency.EUR)
                .withMerchantCustomerNumber("cust-732477")
                .withMerchantInvoiceReferenceNumber("20150112334345")
                .withMerchantOrderReferenceNumber("order-A12223412")
                .withItems(newItems())
                .withNote("Ihr Einkauf bei Spielauto-Versand")
                .withEmailAddress("max@muster.de")
                .withMinimumAge(18)
                .withRedirectUrlAfterSuccess("https://spielauto-versand.de/order/123/success")
                .withRedirectUrlAfterRejection("https://spielauto-versand.de/order/123/rejection")
                .withRedirectUrlAfterCancellation("https://spielauto-versand.de/order/123/cancellation")
                .withRedirectUrlAfterAgeVerificationFailure("https://spielauto-versand.de/order/123/ageverificationfailed")
                .build();
    }

    private static ShippingAddress newAddress() {
        return ShippingAddressBuilder.aShippingAddress()
                .withAddresseeGivenName("Marie")
                .withAddresseeLastName("Mustermann")
                .withCity("Berlin")
                .withCountryCode("DE")
                .withStreet("Hamburger Allee")
                .withStreetNr("12a")
                .withZip("10781")
                .withAdditionalAddressInformation("Quergebäude Links")
                .build();
    }

    private static List<Item> newItems() {
        return Arrays.asList(ItemBuilder.anItem()
                .withEan("800001303")
                .withName("Bobbycar")
                .withPrice(new BigDecimal("25.99"))
                .withQuantity(3)
                .build());
    }

    private MocksFactory() {
    }
}
