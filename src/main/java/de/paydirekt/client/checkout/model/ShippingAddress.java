package de.paydirekt.client.checkout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * The shipping address of the buyer.
 */
public class ShippingAddress {

    private final String addresseeGivenName;
    private final String addresseeLastName;
    private final String company;
    private final String street;
    private final String streetNr;
    private final String additionalAddressInformation;
    private final String zip;
    private final String city;
    private final String countryCode;
    private final String emailAddress;

    /**
     * Constructor.
     *
     * @param addresseeGivenName           Given name. (Mandatory)
     * @param addresseeLastName            Last name. (Mandatory)
     * @param company                      Company name. (Optional)
     * @param street                       The street name. (Optional)
     * @param streetNr                     The street number. (Optional)
     * @param additionalAddressInformation Additional address information. (Optional)
     * @param zip                          The zip code. (Optional for {@link ShoppingCartType#DIGITAL} otherwise mandatory)
     * @param city                         (Optional for {@link ShoppingCartType#DIGITAL} otherwise mandatory)
     * @param countryCode                  (Optional for {@link ShoppingCartType#DIGITAL} otherwise mandatory)
     * @param emailAddress                 (Mandatory for {@link ShoppingCartType#DIGITAL} otherwise optional)
     */
    public ShippingAddress(@JsonProperty("addresseeGivenName") String addresseeGivenName,
                           @JsonProperty("addresseeLastName") String addresseeLastName,
                           @JsonProperty("company") String company,
                           @JsonProperty("street") String street,
                           @JsonProperty("streetNr") String streetNr,
                           @JsonProperty("additionalAddressInformation") String additionalAddressInformation,
                           @JsonProperty("zip") String zip,
                           @JsonProperty("city") String city,
                           @JsonProperty("countryCode") String countryCode,
                           @JsonProperty("emailAddress") String emailAddress) {

        nonNull(addresseeGivenName);
        nonNull(addresseeLastName);

        this.addresseeGivenName = addresseeGivenName;
        this.addresseeLastName = addresseeLastName;
        this.company = company;
        this.street = street;
        this.streetNr = streetNr;
        this.additionalAddressInformation = additionalAddressInformation;
        this.zip = zip;
        this.city = city;
        this.countryCode = countryCode;
        this.emailAddress = emailAddress;
    }

    public String getAddresseeGivenName() {
        return addresseeGivenName;
    }

    public String getAddresseeLastName() {
        return addresseeLastName;
    }

    public String getCompany() {
        return company;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public String getAdditionalAddressInformation() {
        return additionalAddressInformation;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresseeGivenName, addresseeLastName, company, street, streetNr, additionalAddressInformation, zip, city, countryCode, emailAddress);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShippingAddress)) {
            return false;
        }
        final ShippingAddress other = (ShippingAddress) obj;
        return Objects.equals(addresseeGivenName, other.addresseeGivenName)
                && Objects.equals(addresseeLastName, other.addresseeLastName)
                && Objects.equals(company, other.company)
                && Objects.equals(street, other.street)
                && Objects.equals(streetNr, other.streetNr)
                && Objects.equals(additionalAddressInformation, other.additionalAddressInformation)
                && Objects.equals(zip, other.zip)
                && Objects.equals(city, other.city)
                && Objects.equals(countryCode, other.countryCode)
                && Objects.equals(emailAddress, other.emailAddress);
    }
}
