package com.shopApplication.controllers;


import com.shopApplication.models.ItemReview;
import com.shopApplication.models.ItemReviewModel;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.services.ItemReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/itemReview","/itemReviews"})
public class ItemReviewController {

    public final ItemReviewService itemReviewService;

    @Autowired
    public ItemReviewController(ItemReviewService itemReviewService) {
        this.itemReviewService = itemReviewService;
    }

    // return all ItemReviews

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    List<ItemReview> list(){
        return itemReviewService.list();
    }



    // return  by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody ItemReview findById(@RequestParam("id") int id){
        return itemReviewService.findById(id);
    }

    // return  by name

    // add new ItemReview

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@RequestBody ItemReviewModel itemReviewModel){
        return itemReviewService.add(itemReviewModel);
    }



    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody List<ItemReview> delete(@ModelAttribute ItemReview itemReview){
        return itemReviewService.delete(itemReview.getItem_review_id());
    }
}
