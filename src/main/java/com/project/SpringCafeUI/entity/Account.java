package com.project.SpringCafeUI.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {
    private int id;
    private String username;
    private String password;
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



