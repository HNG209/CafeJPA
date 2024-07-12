package com.project.SpringCafeUI.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetail {
    private int id;
    private double unitPrice;
    private int quantity;
    private double discount;
    private Drink drink;
    private Order order;
    public OrderDetail() {}
    public OrderDetail(int id, double unitPrice, int quantity, double discount, Drink drink, Order order) {
        this.setId(id);
        this.setUnitPrice(unitPrice);
        this.setQuantity(quantity);
        this.setDiscount(discount);
        this.setDrink(drink);
        this.setOrder(order);
    }

}

