package com.project.SpringCafeUI.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Employee {
    private int id;
    private String name;
    private int age;
    private boolean sex;
    private String title;
    private double salary;
    private Date startDate;
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
