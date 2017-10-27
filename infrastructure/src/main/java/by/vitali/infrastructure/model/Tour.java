package by.vitali.infrastructure.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * This class describe tour parameters.
 */
@Getter
@Setter
@SuppressWarnings("PMD.ShortClassName" + "PMD.UnusedPrivateField")
@Entity
@Table(name = "tour")
public class Tour implements Serializable {

    private static final long serialVersionUID = -6387314156812978647L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "discount")
    private int discount;

    @Column(name = "duration")
    private int duration;

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

    public Tour() {
        // Empty constructor
    }

    public Tour(final String name, final int price, final int duration, final Country country, final TourType tourType) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.country = country;
        this.tourType = tourType;
    }

    @Override
    public String toString() {
        return "Tour{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + ", discount=" + discount
                + ", duration=" + duration
                + ", departureCity=" + departureCity
                + ", country=" + country
                + ", tourType=" + tourType
                + ", transportType=" + transportType
                + ", hotel=" + hotel
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (id != tour.id) return false;
        if (price != tour.price) return false;
        if (discount != tour.discount) return false;
        if (duration != tour.duration) return false;
        if (name != null ? !name.equals(tour.name) : tour.name != null) return false;
        if (departureCity != tour.departureCity) return false;
        if (country != tour.country) return false;
        if (tourType != tour.tourType) return false;
        if (transportType != tour.transportType) return false;
        if (hotel != null ? !hotel.equals(tour.hotel) : tour.hotel != null) return false;
        return orders != null ? orders.equals(tour.orders) : tour.orders == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + discount;
        result = 31 * result + duration;
        result = 31 * result + (departureCity != null ? departureCity.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }
}
