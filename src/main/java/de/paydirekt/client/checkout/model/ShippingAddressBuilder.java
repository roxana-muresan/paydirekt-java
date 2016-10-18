package de.paydirekt.client.checkout.model;

/**
 * Builder for a {@link ShippingAddress}.
 */
public final class ShippingAddressBuilder {

    private String addresseeGivenName;
    private String addresseeLastName;
    private String company;
    private String street;
    private String streetNr;
    private String additionalAddressInformation;
    private String zip;
    private String city;
    private String countryCode;
    private String emailAddress;

    private ShippingAddressBuilder() {
    }

    public static ShippingAddressBuilder aShippingAddress() {
        return new ShippingAddressBuilder();
    }

    public ShippingAddressBuilder withAddresseeGivenName(String addresseeGivenName) {
        this.addresseeGivenName = addresseeGivenName;
        return this;
    }

    public ShippingAddressBuilder withAddresseeLastName(String addresseeLastName) {
        this.addresseeLastName = addresseeLastName;
        return this;
    }

    public ShippingAddressBuilder withCompany(String company) {
        this.company = company;
        return this;
    }

    public ShippingAddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public ShippingAddressBuilder withStreetNr(String streetNr) {
        this.streetNr = streetNr;
        return this;
    }

    public ShippingAddressBuilder withAdditionalAddressInformation(String additionalAddressInformation) {
        this.additionalAddressInformation = additionalAddressInformation;
        return this;
    }

    public ShippingAddressBuilder withZip(String zip) {
        this.zip = zip;
        return this;
    }

    public ShippingAddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public ShippingAddressBuilder withCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public ShippingAddressBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public ShippingAddress build() {
        ShippingAddress shippingAddress = new ShippingAddress(
                addresseeGivenName,
                addresseeLastName,
                company,
                street,
                streetNr,
                additionalAddressInformation,
                zip,
                city,
                countryCode,
                emailAddress);
        return shippingAddress;
    }
}
