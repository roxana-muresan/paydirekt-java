package de.paydirekt.client.reports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.paydirekt.client.rest.HalResource;
import de.paydirekt.client.rest.Link;

import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;

/**
 * The report about transactions conducted in the shops of a certain merchant.
 */
public class TransactionReport extends HalResource {

    private final List<Transaction> transactions;

    /**
     * Constructor.
     *
     * @param transactions The transactions of the report.
     * @param embedded     Embedded resources.
     * @param links        Links to resources.
     */
    public TransactionReport(
            @JsonProperty("transactions") List<Transaction> transactions,
            @JsonProperty("_embedded") Map<String, Object> embedded,
            @JsonProperty("_links") Map<String, Link> links) {
        super(embedded, links);
        this.transactions = unmodifiableList(transactions);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
