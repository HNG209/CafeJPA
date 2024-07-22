package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`order`")
@Setter
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(columnDefinition = "DATE", nullable = false)
    private Date date;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    private double totalDue;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    private boolean status;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;//many orders can have the same card number

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "order",
                fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Order(int id, Date date, double totalDue, boolean status, String description, Card card,
                 Employee employee) {
        this.setId(id);
        this.setDate(date);
        this.setTotalDue(totalDue);
        this.setStatus(status);
        this.setDescription(description);
        this.setCard(card);
        this.setEmployee(employee);
    }

}


