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

        public Item translateModelToItem(){
            Item item = new Item();
            item.setTitle(this.title);
            item.setCategory(this.category);
            item.setImage(this.image);
            item.setManufacturer(this.manufacturer);
            return item;
        }
}

