package com.shopApplication.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemModel {
        private String title;
        private String manufacturer;
        private String category;
        private Double price;
        private String image;
        private Integer stock;

        public Item translateModelToItem(){
            return new ItemBuilder()
                    .setTitle(this.title)
                    .setCategory(this.category)
                    .setImage(this.image)
                    .setPrice(this.price)
                    .setManufacturer(this.manufacturer)
                    .setStock(this.stock)
                    .build();
        }
}

