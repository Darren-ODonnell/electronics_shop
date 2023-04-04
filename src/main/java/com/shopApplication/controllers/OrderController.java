package com.shopApplication.controllers;


import com.shopApplication.models.Order;
import com.shopApplication.models.OrderModel;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/order","/orders"})
public class OrderController {

    public final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    // return all Items

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    List<Order> list(){
        return orderService.list();
    }



    // return  by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    Order findById(@RequestParam("id") int id){
        return orderService.findById(id);
    }

    // add new Item

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Order add(@RequestBody OrderModel orderModel){
        return orderService.add(orderModel);
    }

    // edit/update a Item record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@ModelAttribute Order item) {
        return orderService.update(item);
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Order> delete(@ModelAttribute Order item){
        return orderService.delete(item.getId());
    }
}

