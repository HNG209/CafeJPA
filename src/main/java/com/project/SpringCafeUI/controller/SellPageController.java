package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.entity.OrderDetail;
import com.project.SpringCafeUI.view.SellPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SellPageController implements ActionListener, MouseListener {
	private final SellPage sellPage;
//	private final BillPage billPage;

	@Autowired
	public SellPageController(@Lazy SellPage sellPage) {
		this.sellPage = sellPage;
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
	
	private void done() {
		int row = sellPage.getOrderJTable().getSelectedRow();
		if (row < 0) {
			showMessage("Lưu ý", "Vui lòng chọn một đơn đặt", JOptionPane.WARNING_MESSAGE);
		} else {
			int option = showConfirm("Thông báo", "Xác nhận hoàn thành?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
//				OrderDAO orderDAO = new OrderDAO();
//				int id = Integer.parseInt(sellPage.getOrderJTable().getValueAt(row, 0).toString());
//				Order order = orderDAO.getOrder("id", id);
//
//				//Cập nhật trạng thái là 1 vì đã hoàn thành
//				orderDAO.updateStatus(order.getId(), true);
//				// End cập nhật trạng thái là 1
//
//				//Cập nhật lại card
//				CardDAO cardDAO = new CardDAO();
//				cardDAO.updateStatus(order.getCard().getId(), true);
				//End cập nhật lại card
				
				sellPage.getDfOrderTableModel().removeRow(row);
				resetTableDrink();
				
				//Load lại hóa đơn bên trang hóa đơn
//				billPage.getBillPageController().resetTableOrder();
//				billPage.getBillPageController().loadTableOrder();
				//End load lại hóa đơn bên trang hóa đơn
			}
		}
	}

	private void cancel() {
		int row = sellPage.getOrderJTable().getSelectedRow();
		if (row < 0) {
			showMessage("Lưu ý", "Vui lòng chọn một đơn đặt để hủy", JOptionPane.WARNING_MESSAGE);
		} else {
			int option = showConfirm("Thông báo", "Xác nhận hủy?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
//				OrderDAO orderDAO = new OrderDAO();
//				int id = Integer.parseInt(sellPage.getOrderJTable().getValueAt(row, 0).toString());
//				Order order = orderDAO.getOrder("id", id);
//
//				//Xóa bên bảng order detail trước
//				OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//				orderDetailDAO.deleteOrderDetail(order.getId());
//				//End xóa bên bảng order detail trước
//
//				//Xóa bên bảng Order
//				orderDAO.deleteOrder(order.getId());
//				//End xóa bên bảng order
//
//				//Cập nhật lại status cho card
//				CardDAO cardDAO = new CardDAO();
//				cardDAO.updateStatus(order.getCard().getId(), true);
				//End cập nhật lại status cho card
				
				sellPage.getDfOrderTableModel().removeRow(row);
				resetTableDrink();
//				billPage.getBillPageController().resetTableOrder();
//				billPage.getBillPageController().loadTableOrder();
			}
		}
	}

	

	//Reset table
	private void resetTableDrink() {
		sellPage.getDfDrinkTableModel().setRowCount(0);
		sellPage.getDrinkJTable().repaint();
	}
	
	public void resetTableOrder() {
		sellPage.getDfOrderTableModel().setRowCount(0);
		sellPage.getOrderJTable().repaint();
	}
	//End reset table
	
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
