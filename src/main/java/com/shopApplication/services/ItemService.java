package com.shopApplication.services;

import com.shopApplication.controllers.SearchStrategy.*;
import com.shopApplication.enums.MessageTypes;
import com.shopApplication.exceptions.MyMessageResponse;
import com.shopApplication.models.Item;
import com.shopApplication.models.ItemModel;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

        ItemRepository itemRepository;

        @Autowired
        public ItemService(ItemRepository itemRepository) {
            this.itemRepository = itemRepository;
        }

        // return all items

        public List<Item> searchByCategory(String category){
            List<Item> items = itemRepository.searchByCategory(category).orElse(new ArrayList<>());
            if(items.isEmpty()) new MyMessageResponse("Item category: %d not found", MessageTypes.WARN);
            return items;
        }

    public List<Item> searchByManufacturer(String manufacturer){
        List<Item> items = itemRepository.searchByManufacturer(manufacturer).orElse(new ArrayList<>());
        if(items.isEmpty()) new MyMessageResponse("Item manufacturer: %d not found", MessageTypes.WARN);
        return items;
    }

    public List<Item> searchByTitle(String title){
        List<Item> items = itemRepository.searchByTitle(title).orElse(new ArrayList<>());
        if(items.isEmpty()) new MyMessageResponse("Item title: %d not found", MessageTypes.WARN);
        return items;
    }

        public List<Item> list(){
            List<Item> items = itemRepository.findAll();
            if(items.isEmpty()) new MyMessageResponse("Error: No items listed", MessageTypes.WARN);
            return items;
        }

        public Item findByTitle(String title){
            Item Item = itemRepository.findByTitle(title).orElse(new Item());
            if (!Item.getTitle().equals(title))
                new MyMessageResponse(String.format("Item name: %d not found", title), MessageTypes.ERROR);
            return Item;
        }


        // return Item by id

        public Item findById( int id){
            Item Item = itemRepository.findById(id).orElse(new Item());
            if(Item.getId()==null)
                new MyMessageResponse(String.format("Item id: %d not found", id), MessageTypes.ERROR);
            return Item;
        }

        // check if a Itemname exists

        public Boolean existsByTitle( String title){
            return itemRepository.existsByTitle(title);
        }

        // check if a email exists

        public ResponseEntity<MessageResponse> add(ItemModel item){

            if(!itemRepository.existsByTitle(item.getTitle())) {
                itemRepository.save(item.translateModelToItem());
                return ResponseEntity.ok(new MyMessageResponse("new Item added", MessageTypes.INFO));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Itemname already exists", MessageTypes.WARN));
            }

        }

        // delete by id

        public List<Item> delete(int id){
            if(itemRepository.existsById(id)) {
                itemRepository.deleteById(id);
                ResponseEntity.ok(new MyMessageResponse("Stat deleted with id: " + id, MessageTypes.INFO));
            } else {
                ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete Stat with id: " + id, MessageTypes.WARN));
            }
            return list();
        }
        public ResponseEntity<MessageResponse> deleteById( int id){
            if(itemRepository.existsById(id)) {
                itemRepository.deleteById(id);
                return ResponseEntity.ok(new MyMessageResponse("Item deleted with id: " + id, MessageTypes.INFO));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Cannot delete Item with id: " + id, MessageTypes.WARN));
            }

        }

        // edit/update a Item record - only if record with id exists

    @Transactional
    public List<Item> update(int id, int stock){

            // check if exists first
            // then update
            if(itemRepository.existsById(id))
                itemRepository.updateStockById(id, stock);
            return itemRepository.findAll();

        }

        public List<Item> search(String filter, String prompt) {
            SearchStrategy strategy = null;
            switch (filter) {
                case "category":
                    strategy = new CategorySearchStrategy(itemRepository);
                    break;
                case "title":
                    strategy = new TitleSearchStrategy(itemRepository);
                    break;
                case "price":
                    strategy = new PriceSearchStrategy(itemRepository);
                    break;
                case "manufacturer":
                    strategy = new ManufacturerSearchStrategy(itemRepository);
                    break;
            }

            if(strategy != null)
                return strategy.search(prompt);
            else
                return itemRepository.findAll();

        }
}
