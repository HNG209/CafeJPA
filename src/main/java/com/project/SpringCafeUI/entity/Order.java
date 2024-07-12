package com.project.SpringCafeUI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Setter
@Getter
public class Order {

    private int id;
    private Date date;
    private double totalDue;
    private boolean status;
    private String description;
    private Card card;
    private Employee employee;
    public Order() {}
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


