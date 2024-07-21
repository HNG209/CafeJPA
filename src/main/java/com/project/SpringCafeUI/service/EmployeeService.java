package com.project.SpringCafeUI.service;

import com.project.SpringCafeUI.entity.Employee;
import com.project.SpringCafeUI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void save(Employee employee){
        employeeRepository.save(employee);
    }
}
