package de.paydirekt.client.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.unmodifiableMap;

/**
 * Provides HAL Resource functionality to a subclass.
 */
public class HalResource {

    private static final String SELF_REL = "self";

    private final Map<String, Object> embedded;
    private final Map<String, Link> links;

    /**
     * Constructor.
     *
     * @param embedded Embedded resources.
     * @param links    Links to resources.
     */
    public HalResource(@JsonProperty("_embedded") Map<String, Object> embedded,
                       @JsonProperty("_links") Map<String, Link> links) {
        this.embedded = embedded != null ? unmodifiableMap(embedded) : emptyMap();
        this.links = links != null ? unmodifiableMap(links) : emptyMap();
    }

    public Map<String, Object> getEmbeddedResources() {
        return embedded;
    }

    public List<Object> getLinks() {
        return new ArrayList<>(links.values());
    }

    public boolean hasLink(String rel) {
        return links.containsKey(rel);
    }

    public Link getLink(String rel) {
        if (!hasLink(rel)) {
            throw new IllegalStateException(String.format("There exists no %s link", rel));
        }
        return links.get(rel);
    }

    public Link getSelfLink() {
        return getLink(SELF_REL);
    }

}
