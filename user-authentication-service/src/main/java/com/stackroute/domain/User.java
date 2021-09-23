package com.stackroute.domain;

import javax.persistence.*;

/**
 * to define an entity
 */
@Entity
@Table(name = "User")
public class User {

    /**
     * @Id annotation to make id variable as Primary key
     */
    @Id
    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * default constructor
     */
    public User() {
        super();
    }

    /**
     * parameterized constructor
     */
    public User(String id, String password) {
        super();
        this.id = id;
        this.password = password;
    }

    public User(String id, String password, Role role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
