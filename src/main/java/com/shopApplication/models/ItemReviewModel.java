package com.shopApplication.models;

import com.shopApplication.repositories.ItemRepository;
import com.shopApplication.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ItemReviewModel {

        private int user_id;

        private int item_id;

        private Integer rating;

        private String comment;


    public ItemReview translateModelToItemReview(UserRepository userRepository, ItemRepository itemRepository){
        ItemReview itemReview = new ItemReview();
        itemReview.setUser(userRepository.findById(user_id).orElse(new User()));
        itemReview.setItem(itemRepository.findById(item_id).orElse(new Item()));
        itemReview.setComment(comment);
        itemReview.setRating(rating);

        return itemReview;
    }
}
