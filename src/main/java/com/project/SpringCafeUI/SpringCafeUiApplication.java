package com.project.SpringCafeUI;

import com.project.SpringCafeUI.entity.Card;
import com.project.SpringCafeUI.repository.CardRepository;
import com.project.SpringCafeUI.view.LoginPage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;

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

//	@Bean
//	CommandLineRunner commandLineRunner(CardRepository repository){
//		return args -> {
//			repository.save(new Card(1, true));
//			repository.save(new Card(2, true));
//			repository.save(new Card(3, true));
//			repository.save(new Card(4, true));
//			repository.save(new Card(5, true));
//			repository.save(new Card(6, true));
//			repository.save(new Card(7, true));
//			repository.save(new Card(8, true));
//			repository.save(new Card(9, true));
//			repository.save(new Card(10, true));
//			repository.save(new Card(11, true));
//			repository.save(new Card(12, true));
//			repository.save(new Card(13, true));
//			repository.save(new Card(14, true));
//			repository.save(new Card(15, true));
//			repository.save(new Card(16, true));
//			repository.save(new Card(17, true));
//			repository.save(new Card(18, true));
//			repository.save(new Card(19, true));
//			repository.save(new Card(20, true));
//
//		};
//	}
}
