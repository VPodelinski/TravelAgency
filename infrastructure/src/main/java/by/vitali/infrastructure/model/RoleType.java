package by.vitali.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This enum lists user role.
 */
@Entity
@Table(name = "role")
public enum RoleType {
    /**
     * The role is customer for other users.
     */
    CUSTOMER,

    /**
     *The role is travel-agency for administrator.
     */
    TRAVEL_AGENT

}
