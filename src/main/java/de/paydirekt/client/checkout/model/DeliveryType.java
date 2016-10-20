package de.paydirekt.client.checkout.model;

/**
 * Represents the type of delivery.
 */
public enum DeliveryType {

    /**
     * Destination is a normal address.
     */
    STANDARD,

    /**
     * Destination is a packing station.
     */
    PACKSTATION,

    /**
     * Goods are picked up at the market.
     */
    STORE_PICKUP
}
