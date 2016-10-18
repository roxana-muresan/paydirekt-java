package de.paydirekt.client.testutil;

/**
 * Represents the request body for the retrieval of a user authorization token.
 */
public class ObtainUserTokenRequest {

    private static final String GRANT_TYPE = "password";

    private final String processId;
    private final String password;
    private final String username;

    public ObtainUserTokenRequest(String processId, String password, String username) {
        this.processId = processId;
        this.password = password;
        this.username = username;
    }

    public String getProcessId() {
        return processId;
    }

    public String getGrantType() {
        return GRANT_TYPE;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
