package de.paydirekt.client.capture.model;

/**
 * Types of the capture transaction.
 */
public enum CaptureType {

    /**
     * Capture for a {@link de.paydirekt.client.checkout.model.CheckoutType#DIRECT_SALE}
     */
    CAPTURE_DIRECT_SALE,

    /**
     * Capture for a {@link de.paydirekt.client.checkout.model.CheckoutType#ORDER}
     */
    CAPTURE_ORDER
}
