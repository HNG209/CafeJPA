package com.project.SpringCafeUI.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "account")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", columnDefinition = "TEXT", nullable = false)
    private String username;

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(name = "`partition`" , nullable = false)
    private int partition;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Account(int id, String username, String password, int partition, Employee employee) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setPartition(partition);
        this.setEmployee(employee);
    }

    public boolean checkLogin(String username, String password) {
//        return this.username.equalsIgnoreCase(username) && this.password.equalsIgnoreCase(password) && employee.isStatus();
        return employee.isStatus();
    }


}



