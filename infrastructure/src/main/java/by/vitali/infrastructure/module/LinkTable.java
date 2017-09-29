package by.vitali.infrastructure.module;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LinkTable {
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_action_id")
    private TypeAction typeAction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

}
