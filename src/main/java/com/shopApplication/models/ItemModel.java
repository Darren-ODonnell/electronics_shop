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
            Item item = new Item();
            item.setTitle(this.title);
            item.setCategory(this.category);
            item.setImage(this.image);
            item.setPrice(this.price);
            item.setManufacturer(this.manufacturer);
            item.setStock(this.stock);
            return item;
        }
}

