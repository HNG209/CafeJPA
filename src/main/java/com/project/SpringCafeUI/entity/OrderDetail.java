package com.project.SpringCafeUI.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private double unitPrice;
    private int quantity;
    private double discount;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetail(int id, double unitPrice, int quantity, double discount, Drink drink, Order order) {
        this.setId(id);
        this.setUnitPrice(unitPrice);
        this.setQuantity(quantity);
        this.setDiscount(discount);
        this.setDrink(drink);
        this.setOrder(order);
    }

}

