package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.entity.Card;
import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.entity.OrderDetail;
import com.project.SpringCafeUI.repository.CardRepository;
import com.project.SpringCafeUI.repository.OrderDetailRepository;
import com.project.SpringCafeUI.repository.OrderRepository;
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

	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final CardRepository cardRepository;


	@Autowired
	public SellPageController(@Lazy SellPage sellPage, OrderRepository orderRepository, BillPage billPage, CardNumberPage cardNumberPage, OrderDetailRepository orderDetailRepository, CardRepository cardRepository) {
		this.sellPage = sellPage;
        this.orderRepository = orderRepository;
        this.billPage = billPage;
        this.cardNumberPage = cardNumberPage;
        this.orderDetailRepository = orderDetailRepository;
        this.cardRepository = cardRepository;
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
//				OrderDAO orderDAO = new OrderDAO();
				int id = Integer.parseInt(sellPage.getOrderJTable().getValueAt(row, 0).toString());
				Order order = orderRepository.findById(id).get();

				order.setStatus(true);//set order status to true(done)

				Card card = order.getCard();
				card.setStatus(true);
				cardRepository.save(card);//make the tag available again
				cardNumberPage.update();

				orderRepository.save(order);

				billPage.loadTableOrder();//reload order table on billpage


				sellPage.getDfOrderTableModel().removeRow(row);
				resetTableDrink();
			}
		}
	}

	@Transactional
	public Order getOrderById(int id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("cannot found id"));
		Hibernate.initialize(order.getOrderDetails());
		return order;
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

				Order order = this.getOrderById(id);

				Card card = order.getCard();
				card.setStatus(true);//make the tag available again
				cardRepository.save(card);
				cardNumberPage.update();

				orderRepository.deleteById(id);//delete the order itself
				sellPage.getDfOrderTableModel().removeRow(row);
				billPage.loadTableOrder();
				resetTableDrink();
			}
		}
	}

	

	//Reset table
	private void resetTableDrink() {
		sellPage.getDfDrinkTableModel().setRowCount(0);
		sellPage.getDrinkJTable().repaint();
	}
	
//	public void resetTableOrder() {
//		sellPage.getDfOrderTableModel().setRowCount(0);
//		sellPage.getOrderJTable().repaint();
//	}
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
