package de.paydirekt.client.testutil;

import de.paydirekt.client.rest.HalResource;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Custom assertions.
 */
public final class Assertions {

    public static void assertLinkPresent(HalResource resource, String rel) {
        assertThat(resource.getLink(rel), notNullValue());
        assertThat(resource.getLink(rel).getHref(), not(isEmptyOrNullString()));
    }

    private Assertions() {
    }
}
