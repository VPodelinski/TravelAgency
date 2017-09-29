package by.vitali.infrastructure.module;

import by.vitali.infrastructure.module.enums.HotelCategory;
import by.vitali.infrastructure.module.enums.TypeOfMeals;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 2L;

    @Column(name = "id")
    int id;

    @Column(name = "adress")
    private Address address; //LOOK LATER do like workbook

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private HotelCategory category;

    @Column(name = "type_of_meals")
    @Enumerated(EnumType.STRING)
    private TypeOfMeals typeOfMeals;

    @OneToMany(mappedBy = "hotel")
    private Set<Tour> tours;
}
