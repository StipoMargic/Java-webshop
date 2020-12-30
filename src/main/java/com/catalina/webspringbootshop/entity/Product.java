package com.catalina.webspringbootshop.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", unique = true)
    @NotEmpty
    @NotNull
    private String name;

    @Column(name = "price")
    @NotEmpty
    @NotNull
    private float price;

    @NotEmpty
    @NotNull
    @Column(name = "unit_in_stock")
    private int unit_in_stock;

    @NotEmpty
    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "listProducts", fetch = FetchType.EAGER)
    List<Order> orders = new ArrayList<>();


    public Product() {
    }

    public Product(@NotEmpty @NotNull String name, @NotEmpty @NotNull float price, @NotEmpty @NotNull int unit_in_stock, @NotEmpty @NotNull String description, Category category) {
        this.name = name;
        this.price = price;
        this.unit_in_stock = unit_in_stock;
        this.description = description;
        this.category = category;
    }

}
