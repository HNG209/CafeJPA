package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "card")
@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)//đã sửa
    private List<Order> orders;//1 tag can be assigned with many orders

    public Card(int number, boolean status) {
        this.setNumber(number);
        this.setStatus(status);
    }

}
