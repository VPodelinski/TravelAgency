package by.vitali.infrastructure.module;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long OrderId;

//    @ManyToOne
//    @JoinColumn(name = "type_action_id")
//    private TypeAction typeAction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public Order(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(final long orderId) {
        OrderId = orderId;
    }

//    public TypeAction getTypeAction() {
//        return typeAction;
//    }
//
//    public void setTypeAction(final TypeAction typeAction) {
//        this.typeAction = typeAction;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(final Tour tour) {
        this.tour = tour;
    }
}
