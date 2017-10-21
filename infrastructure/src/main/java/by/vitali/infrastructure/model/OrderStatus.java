package by.vitali.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 *This class describe status user's order.
 */
@Getter
@Setter
@SuppressWarnings("PMD.UnusedPrivateField")
@Entity
@Table(name = "order_status")
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = -1337340666060010312L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    @Override
    public String toString() {
        return "OrderStatus{"
                + "id=" + id
                + ", status='" + status + '\''
                +
                '}';
    }
}
