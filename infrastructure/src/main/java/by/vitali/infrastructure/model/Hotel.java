package by.vitali.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@SuppressWarnings("PMD.UnusedPrivateField")
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

    @Override
    public String toString() {
        return "Hotel{"
                + "id=" + id
                + ", address=" + address
                + ", category=" + category
                + ", typeOfMeals=" + typeOfMeals
                //", tours=" + tours +
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != hotel.id) return false;
        if (address != null ? !address.equals(hotel.address) : hotel.address != null) return false;
        if (category != hotel.category) return false;
        if (typeOfMeals != hotel.typeOfMeals) return false;
        return tours != null ? tours.equals(hotel.tours) : hotel.tours == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (typeOfMeals != null ? typeOfMeals.hashCode() : 0);
        result = 31 * result + (tours != null ? tours.hashCode() : 0);
        return result;
    }
}
