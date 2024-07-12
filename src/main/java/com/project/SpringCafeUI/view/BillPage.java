package com.project.SpringCafeUI.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.controller.BillPageController;
import com.project.SpringCafeUI.utils.FontSize;
import com.project.SpringCafeUI.utils.DateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BillPage {

    private JTable orderJTable, drinkJTable;
    private DefaultTableModel dfDrinkTableModel, dfOrderTableModel;

    private JLabel filterJLabel, dayJLabel, monthJLabel, yearJLabel;
    private JComboBox<String> ddJComboBox, mmJComboBox, yyJComboBox;
    private DefaultComboBoxModel<String> ddDefaultComboBoxModel, mmDefaultComboBoxModel, yyDefaultComboBoxModel;
    private JCheckBox dayJChecked, monthJChecked, yearJChecked;

    private JButton searchJButton, deleteJButton, refreshJButton;
    private final BillPageController billPageController;
    private Font font = FontSize.fontBold16();
    private Color color = Color.WHITE;

    private final JPanel billPanel;

    @Autowired
    public BillPage(@Lazy BillPageController billPageController) {
        this.billPageController = billPageController;
        billPanel = new JPanel();
        this.setJPanel();
        this.setComponents();
        this.setLocations();
    }

    private void setLocations() {
        this.setNorth();
        this.setCenter();
        this.setSouth();
    }

    private void setComponents() {
        this.setJLabel();
        this.setJButton();
        this.setJComboBox();
        this.setJTable();
        this.setJCheckBox();
    }

    private void setNorth() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Danh sách các hóa đơn");
        label.setFont(FontSize.fontBold36());
        panel.add(label);

        Box boxMain = Box.createVerticalBox();
        boxMain.add(panel);
        boxMain.add(Box.createVerticalStrut(30));

        Box findGroup = Box.createHorizontalBox();

        findGroup.add(Box.createHorizontalStrut(30));
        findGroup.add(filterJLabel);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(dayJChecked);
        findGroup.add(dayJLabel);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(ddJComboBox);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(monthJChecked);
        findGroup.add(monthJLabel);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(mmJComboBox);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(yearJChecked);
        findGroup.add(yearJLabel);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(yyJComboBox);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(searchJButton);
        findGroup.add(Box.createHorizontalStrut(10));
        findGroup.add(deleteJButton);
        findGroup.add(Box.createHorizontalStrut(30));
        boxMain.add(findGroup);
        billPanel.add(boxMain, BorderLayout.NORTH);
    }

    private void setCenter() {
        Box boxMain = Box.createVerticalBox();

        boxMain.add(Box.createVerticalStrut(20));


        //boxMain.add(findGroup);
        boxMain.add(Box.createVerticalStrut(30));

        Box boxGroup = Box.createHorizontalBox();
        boxGroup.add(Box.createHorizontalStrut(30));

        JScrollPane jScrollPaneOrder = new JScrollPane(orderJTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneOrder.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        JScrollPane jScrollPaneDrink = new JScrollPane(drinkJTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneDrink.setBorder(BorderFactory.createTitledBorder("Danh sách món"));
        boxGroup.add(jScrollPaneOrder);
        boxGroup.add(Box.createHorizontalStrut(10));
        boxGroup.add(jScrollPaneDrink);
        boxGroup.add(Box.createHorizontalStrut(30));
        boxMain.add(boxGroup);

        boxMain.add(Box.createVerticalStrut(20));

        billPanel.add(boxMain, BorderLayout.CENTER);

    }

    private void setSouth() {

    }

    private void setJLabel() {
        filterJLabel = new JLabel("Lọc hóa đơn:");
        filterJLabel.setFont(font);

        dayJLabel = new JLabel("Ngày:");
        dayJLabel.setFont(font);

        monthJLabel = new JLabel("Tháng:");
        monthJLabel.setFont(font);

        yearJLabel = new JLabel("Năm:");
        yearJLabel.setFont(font);
    }

    private void setJButton() {
        searchJButton = new JButton("Tìm");
        searchJButton.setFocusPainted(false);
        searchJButton.setFont(font);
        searchJButton.setBackground(color);
        searchJButton.addActionListener(billPageController);

        deleteJButton = new JButton("Xóa");
        deleteJButton.setFocusPainted(false);
        deleteJButton.setFont(font);
        deleteJButton.setBackground(color);
        deleteJButton.setEnabled(false);
        deleteJButton.addActionListener(billPageController);
    }

    private void setJTable() {
        String[] headerOrder = "Mã HD;Ngày lập;Người lập;Tổng tiền;Trạng thái".split(";");
        dfOrderTableModel = new DefaultTableModel(headerOrder, 0);
        orderJTable = new JTable(dfOrderTableModel);
        orderJTable.setAutoCreateRowSorter(false);
        orderJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        orderJTable.setFont(FontSize.fontPlain16());
        billPageController.loadTableOrder();
        orderJTable.addMouseListener(billPageController);

        String[] headerDrink = "Tên món;Đơn giá;Số lượng".split(";");
        dfDrinkTableModel = new DefaultTableModel(headerDrink, 0);
        drinkJTable = new JTable(dfDrinkTableModel);
        drinkJTable.setAutoCreateRowSorter(false);
        drinkJTable.setFont(FontSize.fontPlain16());
        drinkJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void setJComboBox() {
        ddDefaultComboBoxModel = new DefaultComboBoxModel<String>();
        ddJComboBox = new JComboBox<String>(ddDefaultComboBoxModel);
        ddJComboBox.setFont(font);
        ddJComboBox.setBackground(color);


        mmDefaultComboBoxModel = new DefaultComboBoxModel<String>();
        mmJComboBox = new JComboBox<String>(mmDefaultComboBoxModel);
        mmJComboBox.setFont(font);
        mmJComboBox.setBackground(color);


        yyDefaultComboBoxModel = new DefaultComboBoxModel<String>();
        yyJComboBox = new JComboBox<String>(yyDefaultComboBoxModel);
        yyJComboBox.setFont(font);
        yyJComboBox.setBackground(color);


        this.loadMonthJComboBox();
        this.loadDayJComboBox();
        this.loadYearJComboBox();

        ddJComboBox.setSelectedItem(String.valueOf(LocalDate.now().getDayOfMonth()));
        mmJComboBox.setSelectedItem(String.valueOf(LocalDate.now().getMonthValue()));
        yyJComboBox.setSelectedItem(String.valueOf(LocalDate.now().getYear()));

        mmJComboBox.addActionListener(billPageController);
        yyJComboBox.addActionListener(billPageController);
    }

    private void setJCheckBox() {
        dayJChecked = new JCheckBox();
        dayJChecked.setFont(font);
        dayJChecked.setForeground(color);

        monthJChecked = new JCheckBox();
        monthJChecked.setFont(font);
        monthJChecked.setForeground(color);

        yearJChecked = new JCheckBox();
        yearJChecked.setFont(font);
        yearJChecked.setForeground(color);
    }



    public void loadDayJComboBox() {
        String keyString = this.getMmJComboBox().getSelectedItem().toString();
        int key = Integer.parseInt(keyString);
        int daysInMonth = 0;

        switch (key) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysInMonth = 31;
                break;
            case 2:
                String yearString = this.getYyJComboBox().getSelectedItem().toString();
                int year = Integer.parseInt(yearString);
                daysInMonth = DateTime.isLeapYear(year) ? 29 : 28;
                break;
            default:
                daysInMonth = 30;
                break;
        }

        this.getDdJComboBox().removeAllItems();

        for (int i = 1; i <= daysInMonth; i++) {
            this.getDdJComboBox().addItem(String.valueOf(i));
        }
    }
    public void loadMonthJComboBox() {
        this.getMmJComboBox().removeAllItems();
        for(int i = 1; i <= 12; i++) {
            this.getMmJComboBox().addItem(String.valueOf(i));
        }
    }

    public void loadYearJComboBox() {
        this.getYyJComboBox().removeAllItems();
        for(int i = 2000; i <= LocalDate.now().getYear(); i++) {
            this.getYyJComboBox().addItem(String.valueOf(i));
        }
    }

    private void setJPanel() {
        billPanel.setLayout(new BorderLayout());
    }
}

