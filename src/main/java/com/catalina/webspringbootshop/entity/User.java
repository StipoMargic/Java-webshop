package com.catalina.webspringbootshop.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "user")
public class User {

    //@Column(name = "user_id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username", unique = true)
    @NotEmpty
    @NotNull
    private String username;

    @Column(name = "email", unique = true)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "postal_code")
    private int postalCode;

    @Column(name = "city")
    private String city;

    private String role;

    public User(@NotEmpty @NotNull String username, @Email @NotEmpty @NotNull String email, @NotEmpty @NotNull String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
