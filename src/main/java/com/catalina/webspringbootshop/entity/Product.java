package com.catalina.webspringbootshop.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    /*@Column(name = "category_id")
    @NotEmpty
    @NotNull
    private Category category_id:
    */

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

    @ManyToMany(mappedBy = "listProducts",fetch = FetchType.EAGER)
    Set<Order> listOrders;

    public Product() {
    }

    public Product(@NotEmpty @NotNull String name, @NotEmpty @NotNull float price, @NotEmpty @NotNull int unit_in_stock, @NotEmpty @NotNull String description) {
        this.name = name;
        this.price = price;
        this.unit_in_stock = unit_in_stock;
        this.description = description;
    }
}
