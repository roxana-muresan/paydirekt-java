package de.paydirekt.client.checkout.model;

/**
 * The type of a {@link Checkout}
 */
public enum CheckoutType {

    /**
     * Direct sale with one time payment, the merchant receives a payment guarantee directly.
     */
    DIRECT_SALE,

    /**
     * A reservation with separate captures.
     * The merchant receives a payment guarantee only after an installment has been paid.
     */
    ORDER
}
