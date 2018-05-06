package com.nemo.askquare.model;

import com.nemo.askquare.annotation.Column;
import com.nemo.askquare.annotation.InsertIgnore;
import com.nemo.askquare.annotation.SelectIgnore;

import java.io.Serializable;
import java.sql.Timestamp;

public class Account implements Serializable {

    @InsertIgnore
    private Long id;

    private String login;
    private String password;

    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("second_name")
    private String secondName;

    private String email;

    @InsertIgnore
    private Boolean active;

    @InsertIgnore
    private Timestamp created;

    @SelectIgnore
    @InsertIgnore
    private transient Integer roleId;

    public Account() {
    }

    public Account(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Account(String login, String password, String firstName, String lastName, String secondName, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", created=" + created +
                '}';
    }
}
