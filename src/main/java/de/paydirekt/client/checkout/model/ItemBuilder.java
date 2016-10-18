package de.paydirekt.client.checkout.model;

import java.math.BigDecimal;

/**
 * Builder for a {@link Item}.
 */
public final class ItemBuilder {

    private String ean;
    private int quantity;
    private String name;
    private BigDecimal price;

    private ItemBuilder() {
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withEan(String ean) {
        this.ean = ean;
        return this;
    }

    public ItemBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Item build() {
        Item item = new Item(ean, quantity, name, price);
        return item;
    }
}
