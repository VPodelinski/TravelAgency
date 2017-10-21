package by.vitali.infrastructure.model;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * This class save hotels address.
 */
@ToString
@Getter
@Setter
@SuppressWarnings("PMD.UnusedPrivateField")
public class Address implements Serializable {

    private static final long serialVersionUID = 5274743189482560061L;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number_building")
    private String numberBuilding;

}
