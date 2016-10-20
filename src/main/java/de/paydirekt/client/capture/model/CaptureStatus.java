package de.paydirekt.client.capture.model;

/**
 * Status of the bank authorization of a capture payment.
 */
public enum CaptureStatus {

    /**
     * The bank hasn't authorized the payment yet.
     */
    PENDING,

    /**
     * The payment has been authorized.
     */
    SUCCESSFUL,

    /**
     * The payment was rejected.
     */
    REJECTED
}
