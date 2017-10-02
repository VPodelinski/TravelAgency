package by.vitali.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public enum RoleType {

    USER,
    TRAVEL_AGENT

}
