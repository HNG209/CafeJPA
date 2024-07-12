package com.project.SpringCafeUI.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.project.SpringCafeUI.controller.LoginController;
import com.project.SpringCafeUI.utils.BackgroundColor;
import com.project.SpringCafeUI.utils.FontSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginPage{

    private JLabel usernameJLabel, passwordJLabel;
    private JTextField usernameJTextField;
    private JPasswordField passwordJPasswordField;
    private JButton loginJButton;

    private JFrame frame;

    private final LoginController loginController;

    @Autowired
    public LoginPage(@Lazy LoginController controller){
        this.loginController = controller;
        frame = new JFrame("Login");
    }

    public void show(){
        this.setComponents();
        this.setLocations();
        this.setJFrame();
    }

    private void setLocations() {
        this.setNorth();
        this.setCenter();
        this.setSouth();
    }

    private void setComponents() {
        this.setJLabel();
        this.setJTextField();
        this.setJPasswordField();
        this.setJButton();
    }

    private void setNorth() {
        JLabel label = new JLabel("ĐĂNG NHẬP");
        label.setFont(FontSize.fontPlain36());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(BackgroundColor.orangeSub());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);
    }

    private void setCenter() {
        Box boxMain = Box.createVerticalBox();
        boxMain.add(Box.createVerticalStrut(50));
        Box boxUser = Box.createHorizontalBox();
        boxUser.add(Box.createHorizontalStrut(20));
        boxUser.add(usernameJLabel);
        boxUser.add(Box.createHorizontalStrut(10));
        boxUser.add(usernameJTextField);
        boxUser.add(Box.createHorizontalStrut(20));
        boxMain.add(boxUser);
        boxMain.add(Box.createVerticalStrut(20));
        Box boxPass = Box.createHorizontalBox();
        boxPass.add(Box.createHorizontalStrut(20));
        boxPass.add(passwordJLabel);
        boxPass.add(Box.createHorizontalStrut(10));
        boxPass.add(passwordJPasswordField);
        boxPass.add(Box.createHorizontalStrut(20));
        boxMain.add(boxPass);
        boxMain.add(Box.createVerticalStrut(90));
        frame.add(boxMain, BorderLayout.CENTER);
    }

    private void setSouth() {
        Box boxMain = Box.createVerticalBox();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(loginJButton);
        panel.setBackground(BackgroundColor.orangeSub());
        boxMain.add(panel);

        boxMain.add(Box.createVerticalStrut(50));
        frame.add(boxMain, BorderLayout.SOUTH);
    }

    private void setJLabel() {
        usernameJLabel = new JLabel("Tên đăng nhập:");
        usernameJLabel.setFont(FontSize.fontBold16());

        passwordJLabel = new JLabel("Mật khẩu:");
        passwordJLabel.setFont(FontSize.fontBold16());

        usernameJLabel.setPreferredSize(usernameJLabel.getPreferredSize());
        passwordJLabel.setPreferredSize(usernameJLabel.getPreferredSize());
    }

    private void setJPasswordField() {
        passwordJPasswordField = new JPasswordField();
        passwordJPasswordField.setFont(FontSize.fontBold16());
        passwordJPasswordField.addActionListener(loginController);
    }

    private void setJTextField() {
        usernameJTextField = new JTextField();
        usernameJTextField.setFont(FontSize.fontPlain16());
        usernameJTextField.addActionListener(loginController);
    }

    private void setJButton() {
        loginJButton = new JButton("Đăng nhập");
        loginJButton.setFocusPainted(false);
        loginJButton.setFont(FontSize.fontBold24());
        loginJButton.setBackground(Color.WHITE);
        loginJButton.addActionListener(loginController);
    }

    private void setJFrame() {
        frame.setTitle("Đăng nhập");
        frame.getContentPane().setBackground(BackgroundColor.orangeSub());
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }

//    public static void main(String[] args) {
//        new LoginPage();
//    }

    public JTextField getUsernameJTextField() {
        return usernameJTextField;
    }

    public JPasswordField getPasswordJPasswordField() {
        return passwordJPasswordField;
    }

    public JButton getLoginJButton() {
        return loginJButton;
    }
}