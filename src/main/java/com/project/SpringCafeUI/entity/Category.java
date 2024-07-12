package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Data
@Table(name = " category")
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name =  "name", columnDefinition = "TEXT ", nullable = false)
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category() {
    }

}
