package de.paydirekt.client.checkout.model;

/**
 * The status of a {@link Checkout} from the merchant's view.
 */
public enum CheckoutStatus {

    /**
     * The checkout is created by the merchant.
     * Maybe the checkout is claimed by a user (which is not exposed to the merchant).
     */
    OPEN,

    /**
     * Express only: The user has approved the express checkout but the merchant did not yet execute it.
     */
    PENDING,

    /**
     * Non-express: The user approved the checkout.
     * Express: The user approved the checkout and the merchant executed it.
     * <p>
     * In case of an ORDER, the merchant can now issue captures.
     */
    APPROVED,

    /**
     * The checkout was rejected by the system.
     * - Age Verification
     * - Fraud High Risk
     * - Bank Authorization rejected (Direct Sale only)
     * - 2FA Failed, no retries
     */
    REJECTED,

    /**
     * The checkout was canceled by the user.
     */
    CANCELED,

    /**
     * Only ORDER: No further captures are possible.
     * Refunds are still possible.
     */
    CLOSED,

    /**
     * The checkout is expired.
     * <p>
     * If a checkout has not transitioned from {@link CheckoutStatus#OPEN} to another state within 30 minutes
     * after creation ({@link CheckoutStatus#OPEN}), then it is considered {@link CheckoutStatus#EXPIRED}
     */
    EXPIRED

}
