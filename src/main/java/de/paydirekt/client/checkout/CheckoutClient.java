package de.paydirekt.client.checkout;

import de.paydirekt.client.checkout.model.Checkout;
import de.paydirekt.client.checkout.model.CheckoutRequest;
import de.paydirekt.client.rest.GetRequestBuilder;
import de.paydirekt.client.rest.Link;
import de.paydirekt.client.rest.PostRequestBuilder;
import de.paydirekt.client.rest.RequestExecutor;
import de.paydirekt.client.security.model.AccessToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import static de.paydirekt.client.rest.EndpointProperties.getCheckoutEndpoint;
import static java.util.Objects.requireNonNull;

/**
 * This client connects to the checkout endpoint.
 * Checkouts represent the payment process.
 */
public class CheckoutClient {

    private static final String CLOSE_LINK_REL = "close";

    private final String checkoutEndpoint;
    private final RequestExecutor requestExecutor;

    /**
     * Default constructor, initializes all dependencies.
     */
    public CheckoutClient() {
        this(getCheckoutEndpoint(), new RequestExecutor());
    }

    /**
     * Constructor.
     *
     * @param checkoutEndpoint The URL of the checkout endpoint.
     * @param requestExecutor  The requestExecutor.
     */
    public CheckoutClient(String checkoutEndpoint, RequestExecutor requestExecutor) {
        requireNonNull(requestExecutor);
        requireNonNull(checkoutEndpoint);

        this.checkoutEndpoint = checkoutEndpoint;
        this.requestExecutor = requestExecutor;
    }


    /**
     * Creates a checkout.
     *
     * @param checkoutRequest The request body.
     * @param accessToken     The access token.
     * @return The created checkout.
     */
    public Checkout createCheckout(CheckoutRequest checkoutRequest, AccessToken accessToken) {
        HttpPost post = new PostRequestBuilder(checkoutEndpoint)
                .withDefaultHeaders(accessToken)
                .withEntity(checkoutRequest)
                .build();
        return requestExecutor.executeRequest(post, Checkout.class);
    }

    /**
     * Retrieves a checkout.
     *
     * @param link        The link to the checkout.
     * @param accessToken The access token.
     * @return The retrieved checkout.
     */
    public Checkout getCheckout(Link link, AccessToken accessToken) {
        HttpGet get = new GetRequestBuilder(link.getHref())
                .withDefaultHeaders(accessToken)
                .build();
        return requestExecutor.executeRequest(get, Checkout.class);
    }

    /**
     * Closes a checkout for the capture process.
     * After this no further captures can be created for the given checkout.
     *
     * @param checkout    The checkout to be closed.
     * @param accessToken The access token.
     * @return The closed checkout.
     */
    public Checkout closeCheckout(Checkout checkout, AccessToken accessToken) {
        HttpPost post = new PostRequestBuilder(getCloseEndpoint(checkout))
                .withDefaultHeaders(accessToken)
                .build();
        return requestExecutor.executeRequest(post, Checkout.class);
    }

    private String getCloseEndpoint(Checkout checkout) {
        return checkout.getLink(CLOSE_LINK_REL).getHref();
    }

}
