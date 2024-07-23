package com.project.SpringCafeUI.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.service.OrderService;
import com.project.SpringCafeUI.view.BillPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.project.SpringCafeUI.utils.DateTime;

@Component
public class BillPageController implements ActionListener, MouseListener {
    private final BillPage billPage;
    //private SellPage sellPage;

    @Autowired
    private OrderService orderService;

    @Autowired
    public BillPageController(@Lazy BillPage billPage) {
        this.billPage = billPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(billPage.getMmJComboBox())) {
            selectMonthJComboBox();
        } else if (source.equals(billPage.getYyJComboBox())) {
            selectYearJCombox();
        } else if (source.equals(billPage.getSearchJButton())) {
            search();
        } else {
            if (source.equals(billPage.getDeleteJButton())) {
                delete();
            }
        }
    }

    private void search() {
        boolean dayCheck = billPage.getDayJChecked().isSelected();
        boolean monthCheck = billPage.getMonthJChecked().isSelected();
        boolean yearCheck = billPage.getYearJChecked().isSelected();

        if (!dayCheck && !monthCheck && !yearCheck) {
            resetTableDrink();
            resetTableOrder();
            billPage.loadTableOrder();
        } else {
            Integer day = dayCheck ? Integer.parseInt(billPage.getDdDefaultComboBoxModel().getSelectedItem().toString()) : null;
            Integer month = monthCheck ? Integer.parseInt(billPage.getMmDefaultComboBoxModel().getSelectedItem().toString()) : null;
            Integer year = yearCheck ? Integer.parseInt(billPage.getYyDefaultComboBoxModel().getSelectedItem().toString()) : null;

            List<Order> list = orderService.findByDate(day, month, year);

            billPage.loadTableOrder(list);

            resetTableDrink();
        }
    }

    private void delete() {
        int row = billPage.getOrderJTable().getSelectedRow();
        int id = Integer.parseInt(billPage.getDfOrderTableModel().getValueAt(row, 0).toString());


        int option = showConfirm("Lưu ý", "Bạn chắc chắn muốn xóa?", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            orderService.cancelOrder(id);

            showMessage("Thông báo", "Xóa thành công", JOptionPane.PLAIN_MESSAGE);
            billPage.loadTableOrder();
            resetTableDrink();
        }
    }

    private void selectMonthJComboBox() {
        //Chọn tháng thì luôn chọn vào ngày hôm nay
        String dayString = billPage.getDdDefaultComboBoxModel().getSelectedItem().toString();
        String monthString = billPage.getMmDefaultComboBoxModel().getSelectedItem().toString();
        String yearString = billPage.getYyDefaultComboBoxModel().getSelectedItem().toString();
        billPage.loadDayJComboBox();

        if (monthString.equalsIgnoreCase("2")) {
            int day = Integer.parseInt(dayString);
            int year = Integer.parseInt(yearString);
            if (DateTime.isLeapYear(year)) {
                if (day > 29) {
                    dayString = "29";
                }
            } else {
                if (day > 28) {
                    dayString = "28";
                }
            }
        }
        billPage.getDdDefaultComboBoxModel().setSelectedItem(dayString);
    }

    private void selectYearJCombox() {
        //Chọn năm thì lấy ra tháng ngày vào cho load lại ngày nếu là năm nhuận và tháng 2 mà chọn lớn hơn 29 thì reset lại
        String dayString = billPage.getDdDefaultComboBoxModel().getSelectedItem().toString();
        String monthString = billPage.getMmDefaultComboBoxModel().getSelectedItem().toString();
        String yearString = billPage.getYyDefaultComboBoxModel().getSelectedItem().toString();
        billPage.loadDayJComboBox();

        if (monthString.equalsIgnoreCase("2")) {
            int day = Integer.parseInt(dayString);
            int year = Integer.parseInt(yearString);
            if (DateTime.isLeapYear(year)) {
                if (day > 29) {
                    dayString = "29";
                }
            } else {
                if (day > 28) {
                    dayString = "28";
                }
            }
        }

        billPage.getDdDefaultComboBoxModel().setSelectedItem(dayString);
        billPage.getMmDefaultComboBoxModel().setSelectedItem(monthString);
    }



    //Reset table
    public void resetTableDrink() {
        billPage.getDfDrinkTableModel().setRowCount(0);
        billPage.getDrinkJTable().repaint();
    }

    public void resetTableOrder() {
        billPage.getDfOrderTableModel().setRowCount(0);
        billPage.getOrderJTable().repaint();
    }
    //End reset table

    //Show message
    private void showMessage(String title, String message, int option) {
        JOptionPane.showMessageDialog(billPage.getBillPanel(), message, title, option);

    }

    private int showConfirm(String title, String message, int option) {
        return JOptionPane.showConfirmDialog(billPage.getBillPanel(), message, title, option);
    }
    //End show message

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source.equals(billPage.getOrderJTable())) {
            billPage.getDeleteJButton().setEnabled(true);
            resetTableDrink();
            int row = billPage.getOrderJTable().getSelectedRow();
            billPage.loadTableDrink(Integer.parseInt(billPage.getOrderJTable().getValueAt(row, 0).toString()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
