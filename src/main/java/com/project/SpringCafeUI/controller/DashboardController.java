package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.project.SpringCafeUI.view.CardNumberPage;
import com.project.SpringCafeUI.view.Dashboard;
import com.project.SpringCafeUI.view.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DashboardController implements ActionListener, WindowListener{

	private final Dashboard dashboard;
	private final LoginPage loginPage;
	private final CardNumberPage cardNumberPage;

	@Autowired
	public DashboardController(@Lazy Dashboard dashboard,@Lazy LoginPage loginPage,@Lazy CardNumberPage cardNumberPage) {
		this.dashboard = dashboard;
        this.loginPage = loginPage;
        this.cardNumberPage = cardNumberPage;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		Object source = e.getSource();
		if (command.equalsIgnoreCase("Trang chủ")) {
			cardNumberPage.show();
		} 
		dashboard.getCardLayout().show(dashboard.getCardPanel(), command);
		if (source.equals(dashboard.getLogOutJButton())) {
			logOut();
		}
	}
	
	private void logOut() {
		int option = showConfirm("Thông báo", "Bạn thật sự muốn thoát", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			dashboard.getFrame().dispose();
			loginPage.getFrame().setVisible(true);
			loginPage.getPasswordJPasswordField().setText("");
		}
	}
	
	private int showConfirm(String title, String message, int option) {
		return JOptionPane.showConfirmDialog(dashboard.getFrame(), message, title, option);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		logOut();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
