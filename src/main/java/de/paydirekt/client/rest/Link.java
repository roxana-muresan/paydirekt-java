package de.paydirekt.client.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A resource link.
 */
public class Link {

    private final String href;

    /**
     * Constructor.
     *
     * @param href The location of the resource.
     */
    public Link(@JsonProperty("href") String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

}
