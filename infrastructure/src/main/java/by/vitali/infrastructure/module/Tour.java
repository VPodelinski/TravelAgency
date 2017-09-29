package by.vitali.infrastructure.module;


import by.vitali.infrastructure.module.enums.Country;
import by.vitali.infrastructure.module.enums.DepartureCity;
import by.vitali.infrastructure.module.enums.TourType;
import by.vitali.infrastructure.module.enums.TransportType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tour")
public class Tour implements Serializable {

    private static final long serialVersionUID = 3L;

    @Column(name = "id")
    private int id;

    @Column(name = "name_tour")
    private String nameTour;

    @Column(name = "price")
    private int price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "is_hot")
    private boolean isHot;

    @Column(name = "count_of_nights")
    private int countOfNights;

    @Column(name = "departure_city")
    @Enumerated(EnumType.STRING)
    private DepartureCity departureCity;

    @Column(name = "departure_city")
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "departure_city")
    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @Column(name = "departure_city")
    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "tour")
    private Set<LinkTable> linkTables;


}
