package com.catalina.webspringbootshop.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
@Entity
@Table(name = "users")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private int id;

    @Column(name = "name")
    @NonNull
    @NotEmpty
    public String name;

    public Category() {
    }

    public Category(int id, @NonNull @NotEmpty String name) {
        this.id = id;
        this.name = name;
    }
}
