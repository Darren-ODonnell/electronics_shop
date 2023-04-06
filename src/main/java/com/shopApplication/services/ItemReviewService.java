package com.shopApplication.services;

import com.shopApplication.enums.MessageTypes;
import com.shopApplication.exceptions.MyMessageResponse;
import com.shopApplication.models.ItemReview;
import com.shopApplication.models.ItemReviewModel;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.repositories.ItemRepository;
import com.shopApplication.repositories.ItemReviewRepository;
import com.shopApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemReviewService {

        ItemReviewRepository itemReviewRepository;
        ItemRepository itemRepository;
        UserRepository userRepository;


        @Autowired
        public ItemReviewService(ItemReviewRepository itemReviewRepository, ItemRepository itemRepository, UserRepository userRepository) {
            this.itemReviewRepository = itemReviewRepository;
            this.itemRepository = itemRepository;
            this.userRepository = userRepository;
        }

        // return all items



        public List<ItemReview> list(){
            List<ItemReview> items = itemReviewRepository.findAll();
            if(items.isEmpty()) new MyMessageResponse("Error: No items listed", MessageTypes.WARN);
            return items;
        }




        // return ItemReview by id

        public ItemReview findById( int id){
            ItemReview ItemReview = itemReviewRepository.findById(id).orElse(new ItemReview());
            if(ItemReview.getItem_review_id()==null)
                new MyMessageResponse(String.format("ItemReview id: %d not found", id), MessageTypes.ERROR);
            return ItemReview;
        }

        // check if a ItemReviewname exists

        public ResponseEntity<MessageResponse> add(ItemReviewModel item){

                ItemReview itemReview = itemReviewRepository.save(item.translateModelToItemReview(userRepository));

                //This doesn't need to happen because the database doesn't have a list inside item only the pojo does.

                //This means that when I create a review, any following get requests should just pull the list from the itemReview table
//                itemRepository.getById(item.getItem_id())
//                        .getItemReviews().add(itemReview);

                return ResponseEntity.ok(new MyMessageResponse("new ItemReview added", MessageTypes.INFO));



        }

        // delete by id

        public List<ItemReview> delete(int id){
            if(itemReviewRepository.existsById(id)) {
                itemReviewRepository.deleteById(id);
                ResponseEntity.ok(new MyMessageResponse("Stat deleted with id: " + id, MessageTypes.INFO));
            } else {
                ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete Stat with id: " + id, MessageTypes.WARN));
            }
            return list();
        }
        public ResponseEntity<MessageResponse> deleteById( int id){
            if(itemReviewRepository.existsById(id)) {
                itemReviewRepository.deleteById(id);
                return ResponseEntity.ok(new MyMessageResponse("ItemReview deleted with id: " + id, MessageTypes.INFO));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Cannot delete ItemReview with id: " + id, MessageTypes.WARN));
            }

        }

        // edit/update a ItemReview record - only if record with id exists


}
