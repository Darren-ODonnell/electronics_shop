package com.shopApplication.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
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
    private BigDecimal price;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "image")
    private String image;

}