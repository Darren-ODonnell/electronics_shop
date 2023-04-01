package com.shopApplication.models;

import com.shopApplication.security.UserModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "item_review")
public class ItemReview {
    @Id
    @Column(name = "item_review_id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(name = "rating")
    private Integer rating;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "comment")
    private String comment;

}