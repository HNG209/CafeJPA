package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.project.SpringCafeUI.service.OrderService;
import com.project.SpringCafeUI.view.BillPage;
import com.project.SpringCafeUI.view.CardNumberPage;
import com.project.SpringCafeUI.view.SellPage;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SellPageController implements ActionListener, MouseListener {
	private final SellPage sellPage;
	private final BillPage billPage;
	private final CardNumberPage cardNumberPage;

	@Autowired
	private OrderService orderService;

	@Autowired
	public SellPageController(@Lazy SellPage sellPage, BillPage billPage, CardNumberPage cardNumberPage) {
		this.sellPage = sellPage;
        this.billPage = billPage;
        this.cardNumberPage = cardNumberPage;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(sellPage.getDoneJButton())) {
			done();
		} else {
			if (source.equals(sellPage.getCancelJButton())) {
				cancel();
			}
		}
	}

	@Transactional
	private void done() {
		int row = sellPage.getOrderJTable().getSelectedRow();
		if (row < 0) {
			showMessage("Lưu ý", "Vui lòng chọn một đơn đặt", JOptionPane.WARNING_MESSAGE);
		} else {
			int option = showConfirm("Thông báo", "Xác nhận hoàn thành?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				int id = Integer.parseInt(sellPage.getOrderJTable().getValueAt(row, 0).toString());

				orderService.done(id);

				billPage.loadTableOrder();//reload order table on billpage
				cardNumberPage.update();
				sellPage.getDfOrderTableModel().removeRow(row);
				resetTableDrink();
			}
		}
	}

	@Transactional
	private void cancel() {
		int row = sellPage.getOrderJTable().getSelectedRow();
		if (row < 0) {
			showMessage("Lưu ý", "Vui lòng chọn một đơn đặt để hủy", JOptionPane.WARNING_MESSAGE);
		} else {
			int option = showConfirm("Thông báo", "Xác nhận hủy?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				int id = Integer.parseInt(sellPage.getOrderJTable().getValueAt(row, 0).toString());

				orderService.cancelOrder(id);

				sellPage.getDfOrderTableModel().removeRow(row);
				billPage.loadTableOrder();
				cardNumberPage.update();
				resetTableDrink();
			}
		}
	}

	//Reset table
	private void resetTableDrink() {
		sellPage.getDfDrinkTableModel().setRowCount(0);
		sellPage.getDrinkJTable().repaint();
	}

	//Show message
	private void showMessage(String title, String message, int option) {
		JOptionPane.showMessageDialog(sellPage.getSellPanel(), message, title, option);
		
	}
	
	private int showConfirm(String title, String message, int option) {
		return JOptionPane.showConfirmDialog(sellPage.getSellPanel(), message, title, option);
	}
	//End show message
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source.equals(sellPage.getOrderJTable())) {
			sellPage.getDoneJButton().setEnabled(true);
			sellPage.getCancelJButton().setEnabled(true);
			resetTableDrink();
			int row = sellPage.getOrderJTable().getSelectedRow();
			sellPage.loadTableDrink(Integer.parseInt(sellPage.getOrderJTable().getValueAt(row, 0).toString()));
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
