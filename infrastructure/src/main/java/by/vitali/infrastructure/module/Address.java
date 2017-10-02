package by.vitali.infrastructure.module;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * This class save hotels address
 */
@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = 6L;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number_building")
    private String numberBuilding;

    //Empty comstructor
    public Address(){}

    public Address(String city, String street, String numberBuilding) {
        this.city = city;
        this.street = street;
        this.numberBuilding = numberBuilding;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
