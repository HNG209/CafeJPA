package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card")
@Setter
@Getter
@AllArgsConstructor
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
    public Card(int number, boolean status) {
        this.setNumber(number);
        this.setStatus(status);
    }

}
