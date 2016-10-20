package de.paydirekt.client.reports.model;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

/**
 * Encapsulates the query parameters needed for the creation of a {@link TransactionReport}.
 */
public class TransactionReportQueryParameters {

    private final Optional<ZonedDateTime> from;
    private final Optional<ZonedDateTime> to;
    private final List<String> fields;

    /**
     * Constructor.
     */
    public TransactionReportQueryParameters() {
        this.from = Optional.empty();
        this.to = Optional.empty();
        this.fields = Collections.emptyList();
    }

    /**
     * Constructor.
     *
     * @param from   Time stamp in ISO-8601 format, from which transactions should be considered. (Optional)
     * @param to     Time stamp in ISO-8601 format, until which transactions should be considered. (Optional)
     * @param fields The fields of the transactions which to consider in the report, if left empty all fields will be considered. (Optional)
     */
    public TransactionReportQueryParameters(ZonedDateTime from, ZonedDateTime to, List<String> fields) {
        this.from = Optional.ofNullable(from);
        this.to = Optional.ofNullable(to);
        this.fields = unmodifiableList(fields);
    }

    public Optional<ZonedDateTime> getFrom() {
        return from;
    }

    public Optional<ZonedDateTime> getTo() {
        return to;
    }

    public List<String> getFields() {
        return fields;
    }
}
