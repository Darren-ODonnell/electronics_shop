package com.shopApplication.services;

import com.shopApplication.enums.MessageTypes;
import com.shopApplication.exceptions.MyMessageResponse;
import com.shopApplication.models.*;
import com.shopApplication.payload.response.MessageResponse;
import com.shopApplication.repositories.ItemRepository;
import com.shopApplication.repositories.OrderItemRepository;
import com.shopApplication.repositories.OrderRepository;
import com.shopApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

        OrderRepository orderRepository;
        UserRepository userRepository;
        ItemRepository itemRepository;
        OrderItemRepository orderItemRepository;

        @Autowired
        public OrderService(OrderRepository orderRepository, UserRepository userRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
            this.orderRepository = orderRepository;
            this.userRepository = userRepository;
            this.itemRepository = itemRepository;
            this.orderItemRepository = orderItemRepository;
        }

        // return all items


        public List<Order> list(){
            List<Order> orders = orderRepository.findAll();
            if(orders.isEmpty()) new MyMessageResponse("Error: No items listed", MessageTypes.WARN);
            return orders;
        }

        public List<Order> findAll(){
            List<Order> orders = orderRepository.findAll();
            if(orders.isEmpty()) new MyMessageResponse("Error: No items listed", MessageTypes.WARN);
            return orders;
        }


        // return Item by id

        public Order findById( int id){
            Order order = orderRepository.findById(id).orElse(new Order());
            if(order.getId()==null)
                new MyMessageResponse(String.format("Item id: %d not found", id), MessageTypes.ERROR);
            return order;
        }

        // check if a email exists

        public Order add(OrderModel orderModel){

//            if(!orderRepository.existsByTitle(item.getTitle())) {
                Order order = orderRepository.save(orderModel.translateModelToOrder(userRepository));

                List<OrderItem> orderItems = new ArrayList<>();
                for(OrderItemModel model: orderModel.getOrderItemModels()){
                    orderItems.add(model.translateModelToOrderItem(itemRepository, order));
                }

                orderItemRepository.saveAll(orderItems);
                return order;
//            } else {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Itemname already exists", MessageTypes.WARN));
//            }

        }

        // delete by id

        public List<Order> delete(int id){
            if(orderRepository.existsById(id)) {
                orderRepository.deleteById(id);
                ResponseEntity.ok(new MyMessageResponse("Stat deleted with id: " + id, MessageTypes.INFO));
            } else {
                ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete Stat with id: " + id, MessageTypes.WARN));
            }
            return list();
        }
        public ResponseEntity<MessageResponse> deleteById( int id){
            if(orderRepository.existsById(id)) {
                orderRepository.deleteById(id);
                return ResponseEntity.ok(new MyMessageResponse("Item deleted with id: " + id, MessageTypes.INFO));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Cannot delete Item with id: " + id, MessageTypes.WARN));
            }

        }

        // edit/update a Item record - only if record with id exists

        public ResponseEntity<MessageResponse> update(Order order){

            // check if exists first
            // then update

            if(orderRepository.existsById(order.getId())) {
                orderRepository.save(order);
                return ResponseEntity.ok(new MyMessageResponse("Item record updated", MessageTypes.INFO));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Id does not exist [" + order.getId() + "] -> cannot update record", MessageTypes.WARN));
            }

        }

    }
