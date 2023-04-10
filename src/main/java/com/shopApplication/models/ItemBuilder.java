package com.shopApplication.models;

public class ItemBuilder {
    private String title;
    private String category;
    private String image;
    private double price;
    private String manufacturer;
    private int stock;

    public ItemBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public ItemBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ItemBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemBuilder setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ItemBuilder setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public Item build() {
        Item item = new Item();
        item.setTitle(title);
        item.setCategory(category);
        item.setImage(image);
        item.setPrice(price);
        item.setManufacturer(manufacturer);
        item.setStock(stock);
        return item;
    }
}