package com.catalina.webspringbootshop.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate order_date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
