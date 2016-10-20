package de.paydirekt.client.testutil;

/**
 * Encapsulated the endpoints that are only needed for testing and the test user properties.
 * This can be changed if you wish to test against different environments or with different users.
 */
public final class TestProperties {

    public static final String CHECKOUT_CONFIRM_ENDPOINT = "https://api.sandbox.paydirekt.de/api/checkout/v1/checkouts/{checkoutId}/confirm";
    public static final String USER_TOKEN_OBTAIN_ENDPOINT = "https://api.sandbox.paydirekt.de/api/accountuser/v1/token/obtain";

    public static final String API_KEY = "e81d298b-60dd-4f46-9ec9-1dbc72f5b5df";
    public static final String API_SECRET = "GJlN718sQxN1unxbLWHVlcf0FgXw2kMyfRwD0mgTRME=";
    public static final String TEST_USER_BASIC_AUTH_HEADER = "Basic YnYtY2hlY2tvdXQtd2ViOjhjZEtIZVJ3eDNVNHI3WTlvQ0JkZ1A1OW5DdmNHTWRMa0NmQVNXdVZDdm8=";
    public static final String TEST_USER_NAME = "github_testuser";
    public static final String TEST_USER_HASHED_PW = "PHHrLCitIr3f7m7PFZbdxtmvirHhbJtch_XqUaSTXRrXWdIYBjfgpRA2ICM6qY7s";

    private TestProperties() {
    }
}
