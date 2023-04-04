package com.shopApplication.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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


    @Column(name = "stock", precision = 10, scale = 2)
    private Integer stock;
}