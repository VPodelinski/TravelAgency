package by.vitali.infrastructure.model;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * This class save hotels address.
 */
@RequiredArgsConstructor(staticName = "anAddress", access = AccessLevel.PUBLIC)
public class Address implements Serializable {

    private static final long serialVersionUID = 5274743189482560061L;

    @Column(name = "city")
    private final String city;

    @Column(name = "street")
    private final String street;

    @Column(name = "number_building")
    private final String numberBuilding;

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumberBuilding() {
        return numberBuilding;
    }
}
