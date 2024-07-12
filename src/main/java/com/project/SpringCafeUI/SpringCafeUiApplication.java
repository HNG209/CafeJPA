package com.project.SpringCafeUI;

import com.project.SpringCafeUI.view.LoginPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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

}
