package de.paydirekt.client.refund;

import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.refund.model.Refund;
import de.paydirekt.client.refund.model.RefundRequest;
import de.paydirekt.client.rest.GetRequestBuilder;
import de.paydirekt.client.rest.Link;
import de.paydirekt.client.rest.PostRequestBuilder;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.model.AccessToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import static java.util.Objects.requireNonNull;

/**
 * This client connects to the refund endpoint.
 * Refunds return already approved payments to the buyer.
 * They are always applied to a checkout as a whole and not to individual captures.
 */
public class RefundClient {

    private static final String REFUND_LINK_REL = "refunds";

    private final RequestExecutor requestExecutor;

    /**
     * Default constructor, initializes all dependencies.
     */
    public RefundClient() {
        this(new RequestExecutor());
    }

    /**
     * Constructor.
     *
     * @param requestExecutor The requestExecutor.
     */
    public RefundClient(RequestExecutor requestExecutor) {
        requireNonNull(requestExecutor);

        this.requestExecutor = requestExecutor;
    }

    /**
     * Retrieves a refund.
     *
     * @param link        The link to the refund.
     * @param accessToken The accessToken.
     * @return The retrieved refund.
     */
    public Refund getRefund(Link link, AccessToken accessToken) {
        HttpGet get = new GetRequestBuilder(link.getHref())
                .withDefaultHeaders(accessToken)
                .build();
        return requestExecutor.executeRequest(get, Refund.class);
    }

    /**
     * Creates a refund for a given checkout.
     *
     * @param refundRequest The refund request.
     * @param checkout      The checkout to create a refund for.
     * @param accessToken   The access token.
     * @return The created refund.
     */
    public Refund createRefund(RefundRequest refundRequest, Checkout checkout, AccessToken accessToken) {
        HttpPost post = new PostRequestBuilder(getRefundsEndpoint(checkout))
                .withDefaultHeaders(accessToken)
                .withEntity(refundRequest)
                .build();
        return requestExecutor.executeRequest(post, Refund.class);
    }

    private String getRefundsEndpoint(Checkout checkoutResponse) {
        return checkoutResponse.getLink(REFUND_LINK_REL).getHref();
    }

}
