package de.paydirekt.client.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Messages detailing request errors.
 */
public class ErrorMessage {

    private final ErrorSeverity severity;
    private final String code;
    private final String path;
    private final String reasonCode;
    private final String logref;

    /**
     * Constructor.
     *
     * @param severity   Severity of the error.
     * @param code       Distinct error code.
     * @param path       Path of the error.
     * @param reasonCode Reason code of the error.
     * @param logref     Unique log reference.
     */
    public ErrorMessage(@JsonProperty("severity") ErrorSeverity severity,
                        @JsonProperty("code") String code,
                        @JsonProperty("path") String path,
                        @JsonProperty("reasonCode") String reasonCode,
                        @JsonProperty("logref") String logref) {
        this.severity = severity;
        this.code = code;
        this.path = path;
        this.reasonCode = reasonCode;
        this.logref = logref;
    }

    public ErrorSeverity getSeverity() {
        return severity;
    }

    public String getCode() {
        return code;
    }

    public String getPath() {
        return path;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getLogref() {
        return logref;
    }
}
