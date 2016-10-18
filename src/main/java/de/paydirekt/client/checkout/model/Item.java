package de.paydirekt.client.checkout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * An item of the shopping cart.
 */
public class Item {

    private final String ean;
    private final Integer quantity;
    private final String name;
    private final BigDecimal price;

    /**
     * Constructor.
     *
     * @param ean      International Article Number. (Optional)
     * @param quantity The ordered quantity. (Mandatory)
     * @param name     Description of the item. (Mandatory)
     * @param price    Price of the item. (Mandatory)
     */
    public Item(@JsonProperty("ean") String ean,
                @JsonProperty("quantity") Integer quantity,
                @JsonProperty("name") String name,
                @JsonProperty("price") BigDecimal price) {

        nonNull(quantity);
        nonNull(name);
        nonNull(price);

        this.ean = ean;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ean, quantity, name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Item)) {
            return false;
        }
        final Item other = (Item) obj;
        return Objects.equals(ean, other.ean)
                && Objects.equals(quantity, other.quantity)
                && Objects.equals(name, other.name)
                && Objects.equals(price, other.price);
    }
}
