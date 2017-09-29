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

    @Column(name = "tour_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long tourId;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public long getTourId() {
        return tourId;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }


    public String getNameTour() {
        return nameTour;
    }

    public void setNameTour(String nameTour) {
        this.nameTour = nameTour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public int getCountOfNights() {
        return countOfNights;
    }

    public void setCountOfNights(int countOfNights) {
        this.countOfNights = countOfNights;
    }

    public DepartureCity getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(DepartureCity departureCity) {
        this.departureCity = departureCity;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<LinkTable> getLinkTables() {
        return linkTables;
    }

    public void setLinkTables(Set<LinkTable> linkTables) {
        this.linkTables = linkTables;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "tour")
    private Set<LinkTable> linkTables;
}
