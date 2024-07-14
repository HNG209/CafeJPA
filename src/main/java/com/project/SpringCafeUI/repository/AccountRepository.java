package com.project.SpringCafeUI.repository;

import com.project.SpringCafeUI.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUsernameAndPassword(String username, String password);
}
