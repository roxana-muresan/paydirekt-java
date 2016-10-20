package de.paydirekt.client.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Builder for {@link org.apache.http.client.methods.HttpPost}
 */
public class PostRequestBuilder extends RequestBuilder<PostRequestBuilder, HttpPost> {

    private static final Logger logger = LoggerFactory.getLogger(PostRequestBuilder.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructor.
     *
     * @param url The URL of the request.
     */
    public PostRequestBuilder(String url) {
        super(new HttpPost(url));
    }

    /**
     * Adds an entity to the request.
     *
     * @param entity The entity.
     * @param <E>    The type of the entity.
     * @return The builder.
     */
    public <E> PostRequestBuilder withEntity(E entity) {
        try {
            request.setEntity(new StringEntity(objectMapper.writeValueAsString(entity), UTF_8));
        } catch (JsonProcessingException e) {
            logger.error("Error while trying to serialize {} ", entity);
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    protected PostRequestBuilder getThis() {
        return this;
    }
}
