package com.project.SpringCafeUI.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
@Setter
@Getter
@NoArgsConstructor
@ToString
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

    @Column(name = "role", columnDefinition = "TEXT", nullable = false)
    private String role;

    @Column(name = "salary",columnDefinition = "DOUBLE", nullable = false)
    private double salary;

    @Column(columnDefinition = "DATE", nullable = false)
    private Date startDate;

    @Column(name = "status", columnDefinition = "BOOLEAN", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;

    public Employee(int id, String name, int age, boolean sex, String role, double salary,
                    Date startDate, boolean status) {
        this.setId(id);
        this.setName(name);
        this.setAge(age);
        this.setSex(sex);
        this.setRole(role);
        this.setSalary(salary);
        this.setStartDate(startDate);
        this.setStatus(status);
    }

}
