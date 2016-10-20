package de.paydirekt.client.reports;

import de.paydirekt.client.reports.model.TransactionReport;
import de.paydirekt.client.reports.model.TransactionReportHeader;
import de.paydirekt.client.reports.model.TransactionReportQueryParameters;
import de.paydirekt.client.testutil.TestDataProvider;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static de.paydirekt.client.reports.model.TransactionReportHeader.TRANSACTION_DATE;
import static de.paydirekt.client.testutil.ClientsFactory.newReportsClient;
import static de.paydirekt.client.testutil.TestDataProvider.newTestDataProvider;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Tests the functionality of the {@link ReportsClient}
 */
public class ReportsClientIntegrationTest {

    private static final Integer EXPECTED_NUMBER_OF_FIELDS = TransactionReportHeader.values().length;

    private ReportsClient reportsClient;
    private TestDataProvider testDataProvider;

    @Before
    public void setUp() {
        reportsClient = newReportsClient();
        testDataProvider = newTestDataProvider();
    }

    @Test
    public void shouldRetrieveTransactionsAccordingToQueryParams() {
        TransactionReportQueryParameters request = newTransactionRequest();

        TransactionReport transactionResponse = reportsClient.getTransactions(request,
                testDataProvider.getAccessToken());

        assertThat(transactionResponse, notNullValue());
        transactionResponse.getTransactions().forEach(
                t -> assertDateWithinRange(
                        ZonedDateTime.parse(t.getTransactionDate()),
                        request.getFrom().get(),
                        request.getTo().get()
                )
        );
    }

    @Test
    public void shouldRetrieveCsvReportAccordingToQueryParams() {
        TransactionReportQueryParameters request = newTransactionRequest();

        TransactionReport jsonReport = reportsClient.getTransactions(request,
                testDataProvider.getAccessToken());
        List<CSVRecord> csvReport = reportsClient.getTransactionsAsCSV(request,
                testDataProvider.getAccessToken());

        assertThat(jsonReport, notNullValue());
        assertThat(csvReport, notNullValue());
        assertThat(
                "The JSON-Report and the CSV-Report should have an equal number of records",
                jsonReport.getTransactions().size(),
                is(csvReport.size())
        );
        csvReport.forEach(
                record -> assertThat(
                        "The CSV-Record should have as many fields as there are headers",
                        record.size(),
                        is(EXPECTED_NUMBER_OF_FIELDS)
                )
        );
        csvReport.forEach(
                record -> assertDateWithinRange(
                        ZonedDateTime.parse(record.get(TRANSACTION_DATE.getHeader())),
                        request.getFrom().get(),
                        request.getTo().get()
                )
        );
    }

    private TransactionReportQueryParameters newTransactionRequest() {
        return new TransactionReportQueryParameters(
                ZonedDateTime.of(LocalDate.of(2016, 9, 1), LocalTime.MIN, ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDate.of(2016, 9, 30), LocalTime.MIN, ZoneId.systemDefault()),
                Collections.emptyList());
    }

    private void assertDateWithinRange(ZonedDateTime date, ZonedDateTime from, ZonedDateTime to) {
        assertThat(
                "The date should be within the specified time range",
                date,
                is(both(greaterThanOrEqualTo(from)).and(lessThanOrEqualTo(to))
                )
        );
    }

}
