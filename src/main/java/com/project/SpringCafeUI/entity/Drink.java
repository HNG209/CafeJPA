package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;
    private double unitPrice;
    private String note;
    private String description;
    private boolean status;
    private String pathImage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private	Category category;

    @OneToMany(mappedBy = "drink")
    private List<OrderDetail> orderDetails;

    public Drink(int id) {
        this.id = id;
    }

    public Drink(int id, String name, Category category, double unitPrice, String note, String description,
                 boolean status, String pathImage) {
        this.id = id;
        this.setName(name);
        this.setCategory(category);
        this.setUnitPrice(unitPrice);
        this.setNote(note);
        this.setDescription(description);
        this.setStatus(status);
        this.setPathImage(pathImage);
    }


}
