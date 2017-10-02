package by.vitali.infrastructure.module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "type_action")
public class TypeAction implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_action_id")
    private long typeActionId;

    @Column(name = "action_type")
    private String actionType;

//    @OneToMany(mappedBy = "typeAction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Order> actions;

    public TypeAction() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getTypeActionId() {
        return typeActionId;
    }

    public void setTypeActionId(final long typeActionId) {
        this.typeActionId = typeActionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(final String actionType) {
        this.actionType = actionType;
    }

//    public Set<Order> getActions() {
//        return actions;
//    }
//
//    public void setActions(final Set<Order> actions) {
//        this.actions = actions;
//    }
}
