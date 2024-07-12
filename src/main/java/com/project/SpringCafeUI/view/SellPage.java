package com.project.SpringCafeUI.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.controller.SellPageController;
import com.project.SpringCafeUI.utils.FontSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SellPage{

    private JTable orderJTable, drinkJTable;
    private DefaultTableModel dfOrderTableModel, dfDrinkTableModel;//bảng hàng đợi

    private JButton doneJButton, cancelJButton;
    private final SellPageController sellPageController;

    private Font fontB24 = FontSize.fontBold24();

    private final JPanel sellPanel;


    @Autowired
    public SellPage(@Lazy SellPageController sellPageController) {
        this.sellPageController = sellPageController;
        this.sellPanel = new JPanel();

        this.setJPanel();
        this.setComponents();
        this.setLocations();
    }

    private void setComponents() {
        this.setJButton();
        this.setJTable();
    }

    private void setLocations() {
        this.setNorth();
        this.setCenter();
        this.setSouth();
    }

    private void setNorth() {
        JPanel titleJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleJLabel = new JLabel("Danh sách món đặt");
        titleJLabel.setFont(FontSize.fontBold36());
        titleJPanel.add(titleJLabel);

        sellPanel.add(titleJPanel, BorderLayout.NORTH);
    }

    private void setCenter() {
        Box boxMain = Box.createVerticalBox();

        boxMain.add(Box.createVerticalStrut(20));
        Box boxGroup = Box.createHorizontalBox();

        boxGroup.add(Box.createHorizontalStrut(30));

        JScrollPane jScrollPaneOrder = new JScrollPane(orderJTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneOrder.setBorder(BorderFactory.createTitledBorder("Đang chờ"));
        JScrollPane jScrollPaneDrink = new JScrollPane(drinkJTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneDrink.setBorder(BorderFactory.createTitledBorder("Các món đặt"));
        boxGroup.add(jScrollPaneOrder);
        boxGroup.add(Box.createHorizontalStrut(10));
        boxGroup.add(jScrollPaneDrink);
        boxGroup.add(Box.createHorizontalStrut(30));
        boxMain.add(boxGroup);

        boxMain.add(Box.createVerticalStrut(10));

        sellPanel.add(boxMain, BorderLayout.CENTER);
    }

    private void setSouth() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panel.add(doneJButton);
        panel.add(cancelJButton);

        sellPanel.add(panel, BorderLayout.SOUTH);
    }

    private void setJButton() {
        doneJButton = new JButton("Hoàn thành");
        doneJButton.setFont(fontB24);
        doneJButton.setFocusPainted(false);
        doneJButton.setEnabled(false);
        doneJButton.setBackground(Color.WHITE);
        doneJButton.addActionListener(sellPageController);

        cancelJButton = new JButton("Hủy đơn đặt");
        cancelJButton.setFont(fontB24);
        cancelJButton.setFocusPainted(false);
        cancelJButton.setEnabled(false);
        cancelJButton.setBackground(Color.WHITE);
        cancelJButton.addActionListener(sellPageController);
    }

    private void setJTable() {
        String[] headerOrder = "Mã HD;Số thẻ;Ngày lập;Người lập;Tổng tiền".split(";");
        dfOrderTableModel = new DefaultTableModel(headerOrder, 0);
        orderJTable = new JTable(dfOrderTableModel);
        orderJTable.setAutoCreateRowSorter(false);
        orderJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        orderJTable.addMouseListener(sellPageController);
        orderJTable.setFont(FontSize.fontPlain16());
        sellPageController.loadTableOrder();

        String[] headerDrink = "Tên món;Đơn giá;Số lượng".split(";");
        dfDrinkTableModel = new DefaultTableModel(headerDrink, 0);
        drinkJTable = new JTable(dfDrinkTableModel);
        drinkJTable.setAutoCreateRowSorter(false);
        drinkJTable.setFont(FontSize.fontPlain16());
        drinkJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void setJPanel() {
        sellPanel.setLayout(new BorderLayout());
    }

}
