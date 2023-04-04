package com.shopApplication.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "order_items",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "order_item_id"))
    private List<OrderItem> orderItems = new ArrayList<>();

}