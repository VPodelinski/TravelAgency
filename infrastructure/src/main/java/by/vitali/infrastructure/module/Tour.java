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

    @Id
    @Column(name = "tour_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "tour_type")
    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @Column(name = "transport_type")
    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    //Empty constructor
    public Tour() {
    }

    public Tour(String nameTour, int price, int discount, boolean isHot, int countOfNights, DepartureCity departureCity, Country country, TourType tourType, TransportType transportType, Hotel hotel) {
        this.nameTour = nameTour;
        this.price = price;
        this.discount = discount;
        this.isHot = isHot;
        this.countOfNights = countOfNights;
        this.departureCity = departureCity;
        this.country = country;
        this.tourType = tourType;
        this.transportType = transportType;
        this.hotel = hotel;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getTourId() {
        return tourId;
    }

    public void setTourId(final long tourId) {
        this.tourId = tourId;
    }

    public String getNameTour() {
        return nameTour;
    }

    public void setNameTour(final String nameTour) {
        this.nameTour = nameTour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(final int discount) {
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

    public void setCountOfNights(final int countOfNights) {
        this.countOfNights = countOfNights;
    }

    public DepartureCity getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(final DepartureCity departureCity) {
        this.departureCity = departureCity;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(final TourType tourType) {
        this.tourType = tourType;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(final TransportType transportType) {
        this.transportType = transportType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(final Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(final Set<Order> orders) {
        this.orders = orders;
    }
}
