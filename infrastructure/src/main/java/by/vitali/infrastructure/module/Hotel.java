package by.vitali.infrastructure.module;

import by.vitali.infrastructure.module.enums.HotelCategory;
import by.vitali.infrastructure.module.enums.TypeOfMeals;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 8L;
    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  hotelId;


    private Address address;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private HotelCategory category;

    @Column(name = "type_of_meals")
    @Enumerated(EnumType.STRING)
    private TypeOfMeals typeOfMeals;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tour> tours;

    //Empty constructor
    public Hotel() {
    }

    public Hotel(Address address, HotelCategory category, TypeOfMeals typeOfMeals) {
        this.address = address;
        this.category = category;
        this.typeOfMeals = typeOfMeals;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
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
