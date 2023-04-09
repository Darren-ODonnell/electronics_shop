package com.shopApplication.repositories;

import com.shopApplication.models.ItemReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemReviewRepository extends JpaRepository<ItemReview, Integer> {

    @Query("SELECT i.id, AVG(ir.rating) " +
            "FROM ItemReview ir " +
            "JOIN ir.item i " +
            "GROUP BY ir.item ")
    List<Object[]> findItemReviewsGroupedByItem();

}
