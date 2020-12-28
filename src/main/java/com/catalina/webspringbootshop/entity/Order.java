package com.catalina.webspringbootshop.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Column(name = "order_id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Order_date",updatable = false)
    private Date order_date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    void createDate() {
        this.order_date = new Date();
    }

    public Order() {}
}
