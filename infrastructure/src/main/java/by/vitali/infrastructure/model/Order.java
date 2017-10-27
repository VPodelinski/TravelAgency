package by.vitali.infrastructure.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This class contains information about users, tours, order status.
 */
@ToString
@Getter
@Setter
@SuppressWarnings("PMD.UnusedPrivateField")
@Entity
@Table(name = "`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1205789599377885568L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return tour != null ? tour.equals(order.tour) : order.tour == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        return result;
    }
}
