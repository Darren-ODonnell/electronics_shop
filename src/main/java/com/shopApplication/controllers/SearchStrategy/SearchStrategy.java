package com.shopApplication.controllers.SearchStrategy;

import com.shopApplication.models.Item;

import java.util.List;

public interface SearchStrategy {
    List<Item> search(String prompt);
}