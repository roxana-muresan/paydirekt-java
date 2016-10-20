package de.paydirekt.client.capture;

import de.paydirekt.client.capture.model.Capture;
import de.paydirekt.client.capture.model.CaptureRequest;
import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.rest.GetRequestBuilder;
import de.paydirekt.client.rest.Link;
import de.paydirekt.client.rest.PostRequestBuilder;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.model.AccessToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import static java.util.Objects.requireNonNull;

/**
 * This client connects to the capture endpoint.
 * Captures charge the account of the buyer with a given amount and provide a payment guarantee to the merchant.
 */
public class CaptureClient {

    private static final String CAPTURE_LINK_REL = "captures";

    private final RequestExecutor requestExecutor;

    /**
     * Default constructor, initializes all dependencies.
     */
    public CaptureClient() {
        this(new RequestExecutor());
    }

    /**
     * Constructor.
     *
     * @param requestExecutor The requestExecutor.
     */
    public CaptureClient(RequestExecutor requestExecutor) {
        requireNonNull(requestExecutor);

        this.requestExecutor = requestExecutor;
    }

    /**
     * Retrieves a capture.
     *
     * @param link        The link to the capture.
     * @param accessToken The accessToken.
     * @return The retrieved capture.
     */
    public Capture getCapture(Link link, AccessToken accessToken) {
        HttpGet get = new GetRequestBuilder(link.getHref())
                .withDefaultHeaders(accessToken)
                .build();
        return requestExecutor.executeRequest(get, Capture.class);
    }

    /**
     * Creates a capture for a given checkout.
     *
     * @param captureRequest The request body.
     * @param checkout       The checkout to make the capture for.
     * @param accessToken    The access token.
     * @return The created capture.
     */
    public Capture createCapture(CaptureRequest captureRequest, Checkout checkout, AccessToken accessToken) {
        HttpPost post = new PostRequestBuilder(getCaptureEndpoint(checkout))
                .withDefaultHeaders(accessToken)
                .withEntity(captureRequest)
                .build();
        Capture capture = requestExecutor.executeRequest(post, Capture.class);
        return capture;
    }

    private String getCaptureEndpoint(Checkout checkout) {
        return checkout.getLink(CAPTURE_LINK_REL).getHref();
    }

}
