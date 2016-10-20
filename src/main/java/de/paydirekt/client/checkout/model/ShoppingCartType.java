package de.paydirekt.client.checkout.model;

/**
 * Classifies the items of a {@link Checkout}
 */
public enum ShoppingCartType {

    /**
     * The shopping cart contains physical items only.
     */
    PHYSICAL,

    /**
     * The shopping cart contains digital items only.
     */
    DIGITAL,

    /**
     * The shopping cart contains digital and physical items.
     */
    MIXED
}
