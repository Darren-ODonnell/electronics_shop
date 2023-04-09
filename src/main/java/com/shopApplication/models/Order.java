package com.shopApplication.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private User customer;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "order_items",
//            joinColumns = @JoinColumn(name = "order_number"),
//            inverseJoinColumns = @JoinColumn(name = "order_item_id"))

    @OneToMany(mappedBy = "customerOrderNo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();

}