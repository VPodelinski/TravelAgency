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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long typeActionId;

    @Column(name = "action_type")
    private String actionType;

    @OneToMany(mappedBy = "typeAction")
    private Set<LinkTable> actions;

    public TypeAction() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getTypeActionId() {
        return typeActionId;
    }

    public void setTypeActionId(long typeActionId) {
        this.typeActionId = typeActionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Set<LinkTable> getActions() {
        return actions;
    }

    public void setActions(Set<LinkTable> actions) {
        this.actions = actions;
    }
}
