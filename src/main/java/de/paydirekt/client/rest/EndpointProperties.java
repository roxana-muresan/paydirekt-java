package de.paydirekt.client.rest;

/**
 * Holds the URLs to the endpoints of the paydirekt system.
 * By default, the sandbox-environment is used, to enable requests against the production environment
 * set the system property via '-Dproduction=true'.
 */
public class EndpointProperties {

    private static final String ENDPOINT_SWITCH = "production";

    public static final String SANDBOX_CHECKOUT_ENDPOINT = "https://api.sandbox.paydirekt.de/api/checkout/v1/checkouts";
    public static final String PRODUCTION_CHECKOUT_ENDPOINT = "https://api.paydirekt.de/api/checkout/v1/checkouts";

    public static final String SANDBOX_TOKEN_OBTAIN_ENDPOINT = "https://api.sandbox.paydirekt.de/api/merchantintegration/v1/token/obtain";
    public static final String PRODUCTION_TOKEN_OBTAIN_ENDPOINT = "https://api.paydirekt.de/api/merchantintegration/v1/token/obtain";

    public static final String SANDBOX_TRANSACTION_REPORTS_ENDPOINT = "https://api.sandbox.paydirekt.de/api/reporting/v1/reports/transactions";
    public static final String PRODUCTION_TRANSACTION_REPORTS_ENDPOINT = "https://api.paydirekt.de/api/reporting/v1/reports/transactions";


    public static String getCheckoutEndpoint() {
        return isProduction() ? PRODUCTION_CHECKOUT_ENDPOINT : SANDBOX_CHECKOUT_ENDPOINT;
    }

    public static String getTokenObtainEndpoint() {
        return isProduction() ? PRODUCTION_TOKEN_OBTAIN_ENDPOINT : SANDBOX_TOKEN_OBTAIN_ENDPOINT;
    }

    public static String getTransactionReportsEndpoint() {
        return isProduction() ? PRODUCTION_TRANSACTION_REPORTS_ENDPOINT : SANDBOX_TRANSACTION_REPORTS_ENDPOINT;
    }

    private static boolean isProduction() {
        return Boolean.getBoolean(ENDPOINT_SWITCH);
    }

}
