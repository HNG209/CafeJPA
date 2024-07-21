package com.project.SpringCafeUI.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.project.SpringCafeUI.utils.BackgroundColor;
import com.project.SpringCafeUI.utils.FontSize;
import com.project.SpringCafeUI.controller.CardNumberController;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CardNumberPage {

    private final JButton[] buttons;
    private final Font font = FontSize.fontBold24();
    private final Color backgroudColor = BackgroundColor.orangeSub();
    private final Color textColor = Color.WHITE;
    private final CardNumberController cardNumberController;
    private final int size;
    private final JFrame frame;

    private final HomePage homePage;

    @Autowired
    public CardNumberPage(@Lazy CardNumberController cardNumberController, HomePage homePage) {
        size = cardNumberController.getCards().size();
        this.cardNumberController = cardNumberController;
        this.homePage = homePage;
        buttons = new JButton[size];
        frame = new JFrame("Tag number");
        this.setJButtons();
        this.setCenter();
        this.setJFrame();
    }

    private void setCenter() {
        JPanel panel = new JPanel(new GridLayout(4, 5));
        for(int i = 0; i < size; i++) {
            panel.add(buttons[i]);
        }
        frame.add(panel, BorderLayout.CENTER);
    }

    private void setJButtons() {
        for(int i = 0; i < size; i++) {
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].setFont(font);
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(backgroudColor);

            boolean status = cardNumberController.getCards().get(i).isStatus();

            buttons[i].setEnabled(status);
            buttons[i].addActionListener(cardNumberController);
        }
    }

    @Transactional
    public void update(){
        for(int i = 0;i < size; i++){
            boolean status = cardNumberController.getCards().get(i).isStatus();
            buttons[i].setEnabled(status);
        }
    }

    private void setJFrame() {
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    public void show(){
        frame.setVisible(true);
    }
}
