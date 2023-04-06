package com.shopApplication.controllers;

import com.shopApplication.models.Item;
import com.shopApplication.models.ItemModel;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping({"/item","/items"})
    public class ItemController {

        public final ItemService itemService;

        @Autowired
        public ItemController(ItemService itemService) {
            this.itemService = itemService;
        }


        // return all Items

        @GetMapping(value={"/","/list"} )
        @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
        public @ResponseBody
        List<Item> list(){
            return itemService.list();
        }



        // return  by id

        @GetMapping(value="/findById")
        @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
        public @ResponseBody Item findById(@RequestParam("id") int id){
            return itemService.findById(id);
        }

        // return  by name

        @GetMapping(value="/findByTitle")
        @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
        public @ResponseBody Item findByName(@RequestParam String name) {
            return itemService.findByTitle(name);
        }

        // add new Item

        @PutMapping(value="/add")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public ResponseEntity<MessageResponse> add(@RequestBody ItemModel itemModel){
            return itemService.add(itemModel);
        }

        @GetMapping(value="/search")
        @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
        public @ResponseBody
        List<Item> search(@RequestParam("attributeFilter") String filter,
                          @RequestParam("searchPrompt") String prompt){
            return itemService.search(filter, prompt);
        }

        // edit/update a Item record - only if record with id exists

        @PostMapping(value="/update")
        @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        public List<Item> update(@RequestBody Item item) {
            return itemService.update(item);
        }

        // delete by id

        @DeleteMapping(value="/delete")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public @ResponseBody List<Item> delete(@ModelAttribute Item item){
            return itemService.delete(item.getId());
        }
}
