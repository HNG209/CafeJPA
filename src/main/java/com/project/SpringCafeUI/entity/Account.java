package com.project.SpringCafeUI.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Data
//@Table(name = "account")
@Setter
@Getter
public class Account {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private int id;

//    @Column(name = "username", columnDefinition = "TEXT", nullable = false)
    private String username;

//    @Column(name = "password", columnDefinition = "Text", nullable = false)
    private String password;

//    @Column(name = "partition", columnDefinition = "TEXT" , nullable = false)
    private int partition;


    private Employee employee;

    public Account() {}

    public Account(int id, String username, String password, int partition, Employee employee) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setPartition(partition);
        this.setEmployee(employee);
    }

    public boolean checkLogin(String username, String password) {
        if (this.username.equalsIgnoreCase(username) && this.password.equalsIgnoreCase(password) && employee.isStatus()) {
            if (this.employee.isStatus()) {
                return true;
            }
        }
        return false;
    }

}



