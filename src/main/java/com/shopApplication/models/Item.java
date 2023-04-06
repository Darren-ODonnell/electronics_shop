package com.shopApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "title")
    private String title;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "manufacturer")
    private String manufacturer;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "category")
    private String category;

    @Column(name = "price", precision = 10, scale = 2)
    private Double price;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "image")
    private String image;


    @Column(name = "stock")
    private Integer stock;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    private List<ItemReview> reviews;
}