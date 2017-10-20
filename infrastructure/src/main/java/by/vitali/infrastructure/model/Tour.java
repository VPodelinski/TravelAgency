package by.vitali.infrastructure.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
import java.util.Date;
import java.util.Set;

/**
 * This class describe tour parameters.
 */
//@ToString
@Getter
@Setter
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

    @Column(name = "is_hot")
    private boolean isHot;

    @Column(name = "duration")
    private int duration;

    @Column(name = "date" )
    private Date date;

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
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", isHot=" + isHot +
                ", duration=" + duration +
                ", date=" + date +
                ", departureCity=" + departureCity +
                ", country=" + country +
                ", tourType=" + tourType +
                ", transportType=" + transportType +
                //", hotel=" + hotel +
               // ", orders=" + orders +
                '}';
    }
}
