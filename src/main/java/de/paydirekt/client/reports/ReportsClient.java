package de.paydirekt.client.reports;

import de.paydirekt.client.reports.model.TransactionReport;
import de.paydirekt.client.reports.model.TransactionReportQueryParameters;
import de.paydirekt.client.rest.GetRequestBuilder;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.model.AccessToken;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.paydirekt.client.rest.EndpointProperties.getTransactionReportsEndpoint;
import static java.util.Objects.requireNonNull;

/**
 * This client connects to the reports endpoint.
 * Merchants can obtain reports about the transaction conducted for their webshops.
 */
public class ReportsClient {

    private static final Logger logger = LoggerFactory.getLogger(ReportsClient.class);

    private final String reportsEndpoint;
    private final RequestExecutor requestExecutor;

    /**
     * Default constructor, initializes all dependencies.
     */
    public ReportsClient() {
        this(getTransactionReportsEndpoint(), new RequestExecutor());
    }

    /**
     * Constructor.
     *
     * @param reportsEndpoint The URL of the reports endpoint.
     * @param requestExecutor The requestExecutor.
     */
    public ReportsClient(String reportsEndpoint, RequestExecutor requestExecutor) {
        requireNonNull(reportsEndpoint);
        requireNonNull(requestExecutor);

        this.reportsEndpoint = reportsEndpoint;
        this.requestExecutor = requestExecutor;
    }

    /**
     * Creates a transaction report.
     *
     * @param transactionRequest The request.
     * @param accessToken        The access token.
     * @return The created report.
     */
    public TransactionReport getTransactions(TransactionReportQueryParameters transactionRequest, AccessToken accessToken) {
        URI uri = addQueryParams(reportsEndpoint, transactionRequest);
        HttpGet get = new GetRequestBuilder(uri.toString())
                .withDefaultHeaders(accessToken)
                .build();
        return requestExecutor.executeRequest(get, TransactionReport.class);
    }

    /**
     * Creates a transaction report.
     *
     * @param transactionReportQueryParameters The request.
     * @param accessToken                      The access token.
     * @return The created report as CSV.
     */
    public List<CSVRecord> getTransactionsAsCSV(TransactionReportQueryParameters transactionReportQueryParameters, AccessToken accessToken) {
        URI uri = addQueryParams(reportsEndpoint, transactionReportQueryParameters);
        HttpGet get = new GetRequestBuilder(uri.toString())
                .withAuthorizationHeader(accessToken)
                .withHeader("Accept", "text/csv")
                .build();
        String response = requestExecutor.executeRequest(get);
        return parseCsvRecords(response);
    }

    private List<CSVRecord> parseCsvRecords(String data) {
        List<CSVRecord> csvRecords = null;
        try {
            csvRecords = CSVParser
                    .parse(data, CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withSkipHeaderRecord())
                    .getRecords();
        } catch (IOException e) {
            logger.error("IOException while trying to parse CSV from {}", data);
            throw new RuntimeException(e);
        }
        return csvRecords;
    }

    private URI addQueryParams(String endpoint, TransactionReportQueryParameters transactionReportQueryParameters) {
        try {
            return new URIBuilder(endpoint)
                    .addParameter("from", formatDate(transactionReportQueryParameters.getFrom()))
                    .addParameter("to", formatDate(transactionReportQueryParameters.getTo()))
                    .addParameter("fields", transactionReportQueryParameters.getFields().stream().collect(Collectors.joining(",")))
                    .build();
        } catch (URISyntaxException e) {
            logger.error("URISyntaxException while trying to attach query parameters to {}", endpoint);
            throw new RuntimeException(e);
        }
    }

    private String formatDate(Optional<ZonedDateTime> zonedDateTime) {
        return zonedDateTime
                .map(time -> time.format(DateTimeFormatter.ISO_DATE_TIME))
                .orElse(null);
    }
}
