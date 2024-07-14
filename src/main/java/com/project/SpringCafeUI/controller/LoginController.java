package com.project.SpringCafeUI.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.project.SpringCafeUI.entity.Account;
import com.project.SpringCafeUI.repository.AccountRepository;
import com.project.SpringCafeUI.view.Dashboard;
import com.project.SpringCafeUI.view.LoginPage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LoginController implements ActionListener {

	private final LoginPage loginPage;
	private final Dashboard dashboard;
	private final AccountRepository accountRepository;

	@Autowired
	public LoginController(@Lazy LoginPage loginPage, @Lazy Dashboard dashboard, AccountRepository accountRepository) {
		this.loginPage = loginPage;
        this.dashboard = dashboard;
        this.accountRepository = accountRepository;
    }

	@Override
	@Transactional
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(loginPage.getLoginJButton())
				|| source.equals(loginPage.getUsernameJTextField())
				|| source.equals(loginPage.getPasswordJPasswordField())) {
			login();
		}
	}

	@Transactional
	private void login() {
		String username = loginPage.getUsernameJTextField().getText().trim();
		String password = loginPage.getPasswordJPasswordField().getText().trim();

		if (username.isBlank()) {
			showConfirm("Lưu ý", "Tên đăng nhập không được rỗng", JOptionPane.PLAIN_MESSAGE);
			loginPage.getUsernameJTextField().requestFocus();
			return;
		}

		if (password.isBlank()) {
			showConfirm("Lưu ý", "Mật khẩu không hợp lệ", JOptionPane.PLAIN_MESSAGE);
			loginPage.getPasswordJPasswordField().requestFocus();
			return;
		}

		List<Account> accountList = accountRepository.findByUsernameAndPassword(username, password);
		if(!accountList.isEmpty()) {
			Account account = accountList.get(0);
			if (account.checkLogin(username, password)) {
				showMessage("Thông báo", "Đăng nhập thành công", JOptionPane.PLAIN_MESSAGE);
				loginPage.getFrame().dispose();
				dashboard.getNameJLabel().setText(account.getEmployee().getName());
				dashboard.getPositionJLabel().setText(account.getEmployee().getRole());
				dashboard.getFrame().setVisible(true);

				//Phân vùng
				if (account.getPartition() == 1) {
					dashboard.getCardLayout().show(dashboard.getCardPanel(), "Trang chủ");
					dashboard.getDashboardJButton().setEnabled(true);
					dashboard.getShellJButton().setEnabled(false);
					dashboard.getBillJButton().setEnabled(true);
					dashboard.getProductJButton().setEnabled(false);
					dashboard.getEmployeeJButton().setEnabled(false);
					dashboard.getStatisticalJButton().setEnabled(false);
				} else if (account.getPartition() == 2) {
					dashboard.getCardLayout().show(dashboard.getCardPanel(), "Đơn đặt");
					dashboard.getDashboardJButton().setEnabled(false);
					dashboard.getShellJButton().setEnabled(true);
					dashboard.getBillJButton().setEnabled(true);
					dashboard.getProductJButton().setEnabled(false);
					dashboard.getEmployeeJButton().setEnabled(false);
					dashboard.getStatisticalJButton().setEnabled(false);
				} else {
					dashboard.getCardLayout().show(dashboard.getCardPanel(), "Trang chủ");
					dashboard.getDashboardJButton().setEnabled(true);
					dashboard.getShellJButton().setEnabled(true);
					dashboard.getBillJButton().setEnabled(true);
					dashboard.getProductJButton().setEnabled(true);
					dashboard.getEmployeeJButton().setEnabled(true);
					dashboard.getStatisticalJButton().setEnabled(true);
				}
			}
			else showMessage("Lỗi", "Nhân viên này không còn làm nữa, vui lòng liên hệ với quản lý của bạn", JOptionPane.PLAIN_MESSAGE);
		}
		else showMessage("Lỗi", "Không thể tìm thấy tài khoản của bạn", JOptionPane.PLAIN_MESSAGE);
	}
	
	//Show message
	private void showMessage(String title, String message, int option) {
		JOptionPane.showMessageDialog(loginPage.getFrame(), message, title, option);
		
	}
	
	private int showConfirm(String title, String message, int option) {
		return JOptionPane.showConfirmDialog(loginPage.getFrame(), message, title, option);
	}
	//End show message

}
