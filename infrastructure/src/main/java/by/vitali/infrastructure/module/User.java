package by.vitali.infrastructure.module;

import by.vitali.infrastructure.module.enums.RoleType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This class describe user.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "user")
    private Set<LinkTable> actions;
}
