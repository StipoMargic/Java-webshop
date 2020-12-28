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
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Column(name = "Order_date",updatable = false)
    //private Date order_date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    //@PrePersist
    //void createDate() {
      //  this.order_date = new Date();
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //public Date getOrder_date() {
      //  return order_date;
    //}

    //public void setOrder_date(Date order_date) {
      //  this.order_date = order_date;
    //}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order() {}

    public Order(@NotNull User user) {
        this.user = user;
    }
}
