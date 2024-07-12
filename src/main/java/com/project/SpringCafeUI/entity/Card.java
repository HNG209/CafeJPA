package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card")
@Setter
@Getter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "status", nullable = false)
    private boolean status;
    public Card() {

    }
    public Card(int id, int number, boolean status) {
        this.setId(id);
        this.setNumber(number);
        this.setStatus(status);
    }

}
