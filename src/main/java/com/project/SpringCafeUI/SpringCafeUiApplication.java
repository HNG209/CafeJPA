package com.project.SpringCafeUI;

import com.project.SpringCafeUI.entity.Account;
import com.project.SpringCafeUI.entity.Card;
import com.project.SpringCafeUI.entity.Employee;
import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.repository.AccountRepository;
import com.project.SpringCafeUI.repository.CardRepository;
import com.project.SpringCafeUI.repository.EmployeeRepository;
import com.project.SpringCafeUI.repository.OrderRepository;
import com.project.SpringCafeUI.view.LoginPage;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringCafeUiApplication {

	public static void main(String[] args) {
		if (GraphicsEnvironment.isHeadless()) {
			System.err.println("Headless environment detected. Exiting application.");
			System.exit(1);
		}

		System.setProperty("java.awt.headless", "false");

		ConfigurableApplicationContext context = SpringApplication.run(SpringCafeUiApplication.class, args);

		LoginPage loginPage = context.getBean(LoginPage.class);

		SwingUtilities.invokeLater(loginPage::show);
	}

	@Bean
	CommandLineRunner commandLineRunner(CardRepository cardRepository, AccountRepository accountRepository, EmployeeRepository employeeRepository){
		return args -> {
			if(cardRepository.findAll().isEmpty())
				for(int i = 1; i <= 20; i++)
					cardRepository.save(new Card(i, true));

			if(accountRepository.findAll().isEmpty()){
				Account account = new Account();
				account.setUsername("username");
				account.setPassword("password");

				Employee employee = new Employee();
				employee.setStatus(true);
				employee.setName("Name");
				employee.setAge(21);
				employee.setSalary(10000000.0);
				employee.setSex(false);
				employee.setRole("Quản lý");
				employee.setStartDate(Date.valueOf(LocalDate.now()));

				account.setPartition(0);//quản lý
				account.setEmployee(employee);

				employeeRepository.save(employee);
				accountRepository.save(account);
			}
		};
	}
}
