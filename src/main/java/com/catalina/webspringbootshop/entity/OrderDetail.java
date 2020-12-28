package com.catalina.webspringbootshop.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    private int quantity;
    private int total;
    private Date orderDate;

    public OrderDetail() {}

    public OrderDetail(Order order, int quantity, int total, Date orderDate) {
        this.order = order;
        this.quantity = quantity;
        this.total = total;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(product.getName(), that.product.getName()) &&
                Objects.equals(order.getUser(), that.order.getUser()) &&
                Objects.equals(orderDate, that.orderDate) && Objects.equals(total, that.total) &&
                Objects.equals(quantity, that.quantity);
    }
    @Override
    public int hashCode() {
        return Objects.hash(product.getName(), order.getUser(), orderDate, total, quantity);
    }
}
