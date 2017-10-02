package by.vitali.infrastructure.model;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * This class save hotels address
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 5274743189482560061L;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number_building")
    private String numberBuilding;

    public Address() {
        // Empty constructor
    }

    public Address(final String city, final String street, final String numberBuilding) {
        this.city = city;
        this.street = street;
        this.numberBuilding = numberBuilding;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getNumberBuilding() {
        return numberBuilding;
    }

    public void setNumberBuilding(final String numberBuilding) {
        this.numberBuilding = numberBuilding;
    }
}
