package com.shopApplication.models;

import com.shopApplication.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class OrderModel {

    private Integer customer_id;

    private List<OrderItemModel> orderItemModels;

    public Order translateModelToOrder(UserRepository userRepository) {
        Order order = new Order();
        User user = userRepository.findById(this.customer_id).orElse(new User());
        order.setCustomer(user);

        return order;
    }


}