package com.shopApplication.models;

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


    public ItemReview translateModelToItemReview(UserRepository userRepository){
        ItemReview itemReview = new ItemReview();
        itemReview.setUser(userRepository.getById(user_id));
        itemReview.setComment(comment);
        itemReview.setRating(rating);

        return itemReview;
    }
}
