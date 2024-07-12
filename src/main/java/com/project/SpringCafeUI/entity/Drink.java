package com.project.SpringCafeUI.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Drink {
    private int id;
    private String name;
    private	Category category;
    private double unitPrice;
    private String note;
    private String description;
    private boolean status;
    private String pathImage;

    public Drink() {
        this.id = 0;
    }

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
