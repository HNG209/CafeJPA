package com.project.SpringCafeUI.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Card {
    private int id;
    private int number;
    private boolean status;
    public Card() {

    }
    public Card(int id, int number, boolean status) {
        this.setId(id);
        this.setNumber(number);
        this.setStatus(status);
    }

}
