package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Table(name = "employee")
@Setter
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sex", columnDefinition = "BOOLEAN", nullable = false)
    private boolean sex;

    @Column(name = "title", columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column(name = "salary",columnDefinition = "DOUBLE", nullable = false)
    private double salary;

    @Column(columnDefinition = "DATE", nullable = false)
    private Date startDate;//start_date

    @Column(name = "status", columnDefinition = "BOOLEAN", nullable = false)
    private boolean status;

    public Employee(int id, String name, int age, boolean sex, String title, double salary,
                    Date startDate, boolean status) {
        this.setId(id);
        this.setName(name);
        this.setAge(age);
        this.setSex(sex);
        this.setTitle(title);
        this.setSalary(salary);
        this.setStartDate(startDate);
        this.setStatus(status);
    }

    public Employee() {}

}
