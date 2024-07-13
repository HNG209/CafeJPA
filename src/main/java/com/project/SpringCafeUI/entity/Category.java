package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(name = " category")
@Setter
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name =  "name", columnDefinition = "TEXT", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Drink> drinks;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
