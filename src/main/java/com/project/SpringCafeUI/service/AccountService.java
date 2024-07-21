package com.project.SpringCafeUI.service;

import com.project.SpringCafeUI.entity.Account;
import com.project.SpringCafeUI.entity.Employee;
import com.project.SpringCafeUI.repository.AccountRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class AccountService {
    private Account account;//save the current logged in account

    @Autowired
    private AccountRepository accountRepository;

    public boolean validateLogin(String username, String password){
        List<Account> accountList = accountRepository.findByUsernameAndPassword(username, password);

        if(!accountList.isEmpty()){
            account = accountList.get(0);
            return true;
        }

        return false;
    }
}
