package com.shopApplication.controllers.SearchStrategy;

import com.shopApplication.models.Item;
import com.shopApplication.repositories.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class PriceSearchStrategy implements SearchStrategy {
    private ItemRepository itemRepository;

    public PriceSearchStrategy(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> search(String prompt) {
        return itemRepository.searchByPrice(prompt).orElse(new ArrayList<>());
    }
}