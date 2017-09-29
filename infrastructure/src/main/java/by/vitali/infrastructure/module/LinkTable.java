package by.vitali.infrastructure.module;

import javax.persistence.*;
import java.io.Serializable;

public class LinkTable implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long linkTableId;

    @ManyToOne
    @JoinColumn(name = "type_action_id")
    private TypeAction typeAction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public LinkTable(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getLinkTableId() {
        return linkTableId;
    }

    public void setLinkTableId(long linkTableId) {
        this.linkTableId = linkTableId;
    }

    public TypeAction getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(TypeAction typeAction) {
        this.typeAction = typeAction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
