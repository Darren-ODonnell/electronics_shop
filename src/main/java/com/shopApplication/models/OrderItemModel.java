package com.shopApplication.models;

import com.shopApplication.repositories.ItemRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemModel {

    private Integer item_id;

    private Integer quantity;


    public OrderItem translateModelToOrderItem(ItemRepository itemRepository, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCustomerOrderNo(order);
        orderItem.setItem(itemRepository.findById(item_id).orElse(new Item()));
        orderItem.setQuantity(quantity);
        return orderItem;
    }
}
