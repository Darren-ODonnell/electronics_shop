package com.shopApplication.repositories;

import com.shopApplication.models.ItemReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemReviewRepository extends JpaRepository<ItemReview, Integer> {

}
