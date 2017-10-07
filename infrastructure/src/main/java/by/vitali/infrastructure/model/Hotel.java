package by.vitali.infrastructure.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * This class describe hotel parameters.
 */
@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 6421504641464581929L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Address address;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private HotelCategory category;

    @Column(name = "type_of_meals")
    @Enumerated(EnumType.STRING)
    private TypeOfMeals typeOfMeals;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tour> tours;

    public Hotel() {
        // Empty constructor
    }

    public Hotel(final Address address, final HotelCategory category, final TypeOfMeals typeOfMeals) {
        this.address = address;
        this.category = category;
        this.typeOfMeals = typeOfMeals;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public HotelCategory getCategory() {
        return category;
    }

    public void setCategory(final HotelCategory category) {
        this.category = category;
    }

    public TypeOfMeals getTypeOfMeals() {
        return typeOfMeals;
    }

    public void setTypeOfMeals(final TypeOfMeals typeOfMeals) {
        this.typeOfMeals = typeOfMeals;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(final Set<Tour> tours) {
        this.tours = tours;
    }
}
