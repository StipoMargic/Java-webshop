package com.catalina.webspringbootshop.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    @NonNull
    @NotEmpty
    public String name;

    public Category() {
    }

    public Category(@NonNull @NotEmpty String name) {
        this.name = name;
    }
}
