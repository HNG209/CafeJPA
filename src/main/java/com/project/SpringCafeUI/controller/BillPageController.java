package com.project.SpringCafeUI.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.entity.OrderDetail;
import com.project.SpringCafeUI.view.BillPage;
import com.project.SpringCafeUI.view.SellPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.project.SpringCafeUI.utils.DateTime;

@Component
public class BillPageController implements ActionListener, MouseListener {
    private final BillPage billPage;
    //private SellPage sellPage;

    @Autowired
    public BillPageController(@Lazy BillPage billPage) {
        this.billPage = billPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click");
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

//        if (!dayCheck && !monthCheck && !yearCheck) {
//            resetTableDrink();
//            resetTableOrder();
//            loadTableOrder();
//        } else {
//            int day = dayCheck ? Integer.parseInt(billPage.getDdDefaultComboBoxModel().getSelectedItem().toString()) : -1;
//            int month = monthCheck ? Integer.parseInt(billPage.getMmDefaultComboBoxModel().getSelectedItem().toString()) : -1;
//            int year = yearCheck ? Integer.parseInt(billPage.getYyDefaultComboBoxModel().getSelectedItem().toString()) : -1;
//
//            OrderDAO orderDAO = new OrderDAO();
//
//            List<Order> list = orderDAO.getOrdersByDate(day, month, year);
//
//            resetTableOrder();
//            resetTableDrink();
//            list.stream().forEach(
//                    order -> loadOneRowOrder(billPage.getDfOrderTableModel(), order)
//            );
//        }
    }

    private void delete() {
        int row = billPage.getOrderJTable().getSelectedRow();
        int id = Integer.parseInt(billPage.getDfOrderTableModel().getValueAt(row, 0).toString());

//        OrderDAO orderDAO = new OrderDAO();
//        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//
//        int option = showConfirm("Lưu ý", "Bạn chắc chắn muốn xóa?", JOptionPane.YES_NO_OPTION);
//
//        if (option == JOptionPane.YES_OPTION) {
//            Order order = orderDAO.getOrder("id", id);
//
//            orderDetailDAO.deleteOrderDetail(id);
//            orderDAO.deleteOrder(id);
//            showMessage("Thông báo", "Xóa thành công", JOptionPane.PLAIN_MESSAGE);
//            resetTableOrder();
//            resetTableDrink();
//            loadTableOrder();
//            sellPage = SellPage.getInstace();
//            sellPage.getSellPageController().resetTableOrder();
//            sellPage.getSellPageController().loadTableOrder();
//
//            CardDAO cardDAO = new CardDAO();
//            cardDAO.updateStatus(order.getCard().getId(), true);
//        }
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
    //Load one row table
    private void loadOneRowOrder(DefaultTableModel dfTableModel, Order order) {
        dfTableModel.addRow(new Object[] {
                order.getId(),
                order.getDate(),
                order.getEmployee().getName(),
                order.getTotalDue(),
                order.isStatus() ? "Hoàn thành" : "Đang chờ"
        });
    }

    private void loadOneRowDrink(DefaultTableModel dfTableModel, OrderDetail orderDetail) {
        dfTableModel.addRow(new Object[] {
                orderDetail.getDrink().getName(),
                orderDetail.getDrink().getUnitPrice(),
                orderDetail.getQuantity()
        });
    }
    //End load one row table

    //Load table
    public void loadTableOrder() {
//        OrderDAO orderDAO = new OrderDAO();
//        orderDAO.getOrders().stream()
//                .forEach(order -> loadOneRowOrder(
//                        billPage.getDfOrderTableModel(), order)
//                );
    }

    public void loadTableDrink(int id) {
//        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//        orderDetailDAO.getListOrderDetail("idOrder", id).stream()
//                .filter(orderDetail -> orderDetail.getOrder().getId() == id)
//                .forEach(orderDetail -> loadOneRowDrink(
//                        billPage.getDfDrinkTableModel(), orderDetail)
//                );
    }
    //End load table

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
            loadTableDrink(Integer.parseInt(billPage.getOrderJTable().getValueAt(row, 0).toString()));
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
