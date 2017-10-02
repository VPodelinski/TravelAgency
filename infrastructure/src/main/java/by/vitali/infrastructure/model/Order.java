package by.vitali.infrastructure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1205789599377885568L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeAction typeAction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public Order() {
        // Empty constructor
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public TypeAction getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(final TypeAction typeAction) {
        this.typeAction = typeAction;
    }

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
