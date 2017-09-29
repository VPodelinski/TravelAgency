package by.vitali.infrastructure.module;

import by.vitali.infrastructure.module.enums.HotelCategory;
import by.vitali.infrastructure.module.enums.TypeOfMeals;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 5L;

    @Column(name = "id")
    int id;

    @Column(name = "address")
    private Address address; //LOOK LATER, do like workbook

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private HotelCategory category;

    @Column(name = "type_of_meals")
    @Enumerated(EnumType.STRING)
    private TypeOfMeals typeOfMeals;

    @OneToMany(mappedBy = "hotel")
    private Set<Tour> tours;

    public Hotel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HotelCategory getCategory() {
        return category;
    }

    public void setCategory(HotelCategory category) {
        this.category = category;
    }

    public TypeOfMeals getTypeOfMeals() {
        return typeOfMeals;
    }

    public void setTypeOfMeals(TypeOfMeals typeOfMeals) {
        this.typeOfMeals = typeOfMeals;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }
}
