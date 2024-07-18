package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.entity.*;
import com.project.SpringCafeUI.repository.*;
import com.project.SpringCafeUI.view.CardNumberPage;
import com.project.SpringCafeUI.view.Dashboard;
import com.project.SpringCafeUI.view.HomePage;
import com.project.SpringCafeUI.view.SellPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HomePageController implements ActionListener, MouseListener, DocumentListener{
	private final HomePage homePage;
	private final SellPage sellPage;
	private final Dashboard dashboard;

	private final DrinkRepository drinkRepository;
	private final CardNumberPage cardNumberPage;
	private final CardRepository cardRepository;
	private final EmployeeRepository employeeRepository;
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;

	@Autowired
	public HomePageController(@Lazy HomePage homePage, SellPage sellPage, DrinkRepository drinkRepository, CardNumberPage cardNumberPage, CardRepository cardRepository, EmployeeRepository employeeRepository, Dashboard dashboard, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
		this.homePage = homePage;
        this.sellPage = sellPage;
        this.drinkRepository = drinkRepository;
        this.cardNumberPage = cardNumberPage;
        this.cardRepository = cardRepository;
        this.employeeRepository = employeeRepository;
        this.dashboard = dashboard;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(homePage.getAddJButton())) {
			add();
		} else if (source.equals(homePage.getCategoryJComboBox())) {
			filterTable();
		} else if (source.equals(homePage.getDropJButton())) {
			dropAllBill();
		} else if (source.equals(homePage.getQuantityJTextField())) {
			textFieldChange();
		} else if (source.equals(homePage.getPlusJButton())) {
			plus();
		} else if (source.equals(homePage.getMinusJButton())) {
			minus();
		} else if (source.equals(homePage.getDeleteJButton())) {
			delete();
		} else {
			if (source.equals(homePage.getConfirmJButton())) {
				confirm();
			}
		}
	}
	
	private void add() {
		int selectedRow = homePage.getDrinkJTable().getSelectedRow();
		if(selectedRow < 0) {
			showMessage("Thông báo", "Vui lòng chọn món", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		DefaultTableModel dfBill = homePage.getDfBillDefaultTableModel();
		for(int i = 0; i < dfBill.getRowCount(); i++) {
			if(dfBill.getValueAt(i, 0).toString().equalsIgnoreCase(homePage.getDfDrinkTableModel().getValueAt(selectedRow, 1).toString())) {
				dfBill.setValueAt(Integer.parseInt(dfBill.getValueAt(i, 2).toString()) + 1 , i, 2);
				updateTotal();
				return;
			}
		}
		dfBill.addRow(new String[] {
			homePage.getDfDrinkTableModel().getValueAt(selectedRow, 1).toString(),
			homePage.getDrinkJTable().getValueAt(selectedRow, 3).toString(),
			"1"
		});
		homePage.getDrinkJTable().clearSelection();
		homePage.getImageJLabel().setIcon(new ImageIcon("icons\\icon-java_200px.png"));
		homePage.getNameJLabel().setText("");
		homePage.getUnitPriceJLabel().setText("");
		updateTotal();
	}
	
	private void filterTable() {
		String option = homePage.getDfCategoryComboBoxModel().getSelectedItem().toString();
		if (option.equalsIgnoreCase("Tất cả")) {
			resetTableDrink();
			homePage.loadTable();
		} else {
			resetTableDrink();
			homePage.loadTableByCategory(option);
		}
	}
	
	private void dropAllBill() {
		resetTableBill();
		homePage.getTotalValueJLabel().setText("0.0");
		homePage.getPayByEmployeeJTextField().setText("");
		homePage.getPayByEmployeeChangeValueJLabel().setText("0.0");
		homePage.getCardNumberValueJLabel().setText("");
	}
	
	//Update total
	private void updateTotal() {
		double totalPrice = getTotalPrice();
		homePage.getTotalValueJLabel().setText("" + totalPrice);
	}
	//End update total
	
	private double getTotalPrice() {
		double totalPrice = 0.0;
		DefaultTableModel dfBill = homePage.getDfBillDefaultTableModel();
		for(int i = 0;i < dfBill.getRowCount(); i++) {
			totalPrice += Double.parseDouble(dfBill.getValueAt(i, 1).toString()) * Double.parseDouble(dfBill.getValueAt(i, 2).toString());
		}
		return totalPrice;
	}
	
	//Text field change
	private void textFieldChange() {
		String value = homePage.getQuantityJTextField().getText().trim();//new quantity
		
		DefaultTableModel dfBill = homePage.getDfBillDefaultTableModel();
		int selectedRow = homePage.getBillJTable().getSelectedRow();
		if(selectedRow != -1) {
			if(value.matches("\\d+")) {
				dfBill.setValueAt(value, selectedRow, 2);
				updateTotal();
				homePage.getBillJTable().clearSelection();
				
				homePage.getQuantityJTextField().setText("");
				homePage.getQuantityJTextField().setEditable(false);
				homePage.getQuantityJTextField().setFocusable(false);
				homePage.getPlusJButton().setEnabled(false);
				homePage.getMinusJButton().setEnabled(false);
				homePage.getDeleteJButton().setEnabled(false);
			}
		}
	}
	//End text field change
	
	//Plus
	private void plus() {
		int selectedRow = homePage.getBillJTable().getSelectedRow();
		
		DefaultTableModel dfBill = homePage.getDfBillDefaultTableModel();
		
		int quantity = Integer.parseInt(dfBill.getValueAt(selectedRow, 2).toString());
		
		homePage.getBillJTable().setValueAt(String.valueOf(quantity + 1), selectedRow, 2);
		homePage.getQuantityJTextField().setText(dfBill.getValueAt(homePage.getBillJTable().getSelectedRow(), 2).toString());
		updateTotal();
	}
	//End plus
	
	//Minus
	private void minus() {
		int selectedRow = homePage.getBillJTable().getSelectedRow();
		
		DefaultTableModel dfBill = homePage.getDfBillDefaultTableModel();
		
		int quantity = Integer.parseInt(dfBill.getValueAt(selectedRow, 2).toString());
		
		if (quantity - 1 > 0) {
			homePage.getBillJTable().setValueAt(String.valueOf(quantity - 1), selectedRow, 2);
			homePage.getQuantityJTextField().setText(dfBill.getValueAt(homePage.getBillJTable().getSelectedRow(), 2).toString());
		} else {
			dfBill.removeRow(selectedRow);
		}
		updateTotal();
	}
	//End minus

	//Delete
	private void delete() {
		int selectedRow = homePage.getBillJTable().getSelectedRow();
		homePage.getDfBillDefaultTableModel().removeRow(selectedRow);
		homePage.getQuantityJTextField().setEditable(false);
		homePage.getQuantityJTextField().setFocusable(false);
		homePage.getArrowLeftJButton().setEnabled(false);
		homePage.getArrowRightJButton().setEnabled(false);
		homePage.getAddJButton().setEnabled(false);
		updateTotal();
	}
	//End delete
	
	//Confirm
	private void confirm() {
		if (homePage.getCardNumberValueJLabel().getText().isBlank()) {
			cardNumberPage.show();
		}
		else{
			int rows = homePage.getDfBillDefaultTableModel().getRowCount();

			if(rows == 0){
				showMessage("Thông báo", "chưa có món được chọn!", JOptionPane.PLAIN_MESSAGE);
				return;
			}

			Date date = Date.valueOf(LocalDate.now());
			double total = Double.parseDouble(homePage.getTotalValueJLabel().getText().trim());
			boolean status = false;
			Card card = cardRepository.findByNumber(Integer.parseInt(homePage.getCardNumberValueJLabel().getText())).get(0);
			Optional<Employee> employee = employeeRepository.findById(Integer.parseInt(dashboard.getEmployeeIDJLabel().getText()));

			//save an order with status is set to false(not done)
			Order order = new Order();

			order.setDate(date);
			order.setDescription("");
			order.setStatus(status);
			order.setTotalDue(total);
			order.setCard(card);
			order.setEmployee(employee.get());
			orderRepository.save(order);

			//save each order details to an order
			for(int i = 0; i < rows; i++){
				String drinkName = homePage.getDfBillDefaultTableModel().getValueAt(i, 0).toString();
				int quantity = Integer.parseInt(homePage.getDfBillDefaultTableModel().getValueAt(i, 2).toString());
				Drink drink = drinkRepository.findByName(drinkName).get(0);

				OrderDetail detail = new OrderDetail();

				detail.setDiscount(0.0);
				detail.setUnitPrice(drink.getUnitPrice());
				detail.setQuantity(quantity);
				detail.setDrink(drink);
				detail.setOrder(order);

				orderDetailRepository.save(detail);
			}

			card.setStatus(false);
			cardRepository.save(card);
			cardNumberPage.update();
			sellPage.loadTableOrder();//refresh
			showMessage("Thông báo", "Xác nhận thanh toán thành công", JOptionPane.PLAIN_MESSAGE);
		}
//		try {
//
//			if (homePage.getCardNumberValueJLabel().getText().isBlank()) {
//				new CardNumberPage();
//			} else {
//				Date date = Date.valueOf(LocalDate.now());
//				double totalDue = Double.parseDouble(homePage.getTotalValueJLabel().getText().trim());
//				boolean status = false;
//				String decription = "";
//				Card card = new CardDAO().getCard("cardNumber", Integer.parseInt(homePage.getCardNumberValueJLabel().getText()));
//				Dashboard dashboard = Dashboard.getInstance();
//				Employee employee = new EmployeeDAO().getEmployee("name", dashboard.getNameJLabel().getText());
//				//Thêm vào orders
//				OrderDAO orderDAO = new OrderDAO();
//				Order order = new Order(0, date, totalDue, status, decription, card, employee);
//				orderDAO.addOrder(order);
//				//End thêm vào orders
//
//				//Cập nhật lại trạng thái của card
//				CardDAO cardDAO = new CardDAO();
//				cardDAO.updateStatus(order.getCard().getId(), false);
//				//End cập nhật lại trạng thái của thẻ
//
//				//Thêm các món đặt vào orderDetail
//				OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//				for(int i = 0; i < homePage.getBillJTable().getRowCount(); i++) {
//					Drink drink = new DrinkDAO().getDrink("name", homePage.getDfBillDefaultTableModel().getValueAt(i, 0).toString());
//					double unitPrice = Double.parseDouble(homePage.getDfBillDefaultTableModel().getValueAt(i, 1).toString());
//					int quantity = Integer.parseInt(homePage.getDfBillDefaultTableModel().getValueAt(i, 2).toString());
//					OrderDetail orderDetail = new OrderDetail(0, unitPrice, quantity, 0, drink, order);
//					orderDetailDAO.addOrderDetail(orderDetail);
//				}
//				//End thêm các món đặt vào orderDetail
//				showMessage("Thông báo", "Xác nhận thanh toán thành công", JOptionPane.PLAIN_MESSAGE);
//
//				//Xuất hóa đơn
//				new ExportBill(order);
//				//End xuất hóa đơn
//
//				dropAllBill();
//
//				//Load lại hóa đơn chờ bên trang Đơn đặt
//				sellPage.getSellPageController().resetTableOrder();
//				sellPage.getSellPageController().loadTableOrder();
//				//End load lại hóa đơn chờ bên trang Đơn đặt
//
//				//Load lại hóa đơn bên trang hóa đơn
//				billPage.getBillPageController().resetTableOrder();
//				billPage.getBillPageController().loadTableOrder();
//				//End load lại hóa đơn bên trang hóa đơn
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	//End confirm

	
	//Reset table
	public void resetTableDrink() {
		homePage.getDfDrinkTableModel().setRowCount(0);
		homePage.getDrinkJTable().repaint();
	}
	
	private void resetTableBill() {
		homePage.getDfBillDefaultTableModel().setRowCount(0);
		homePage.getBillJTable().repaint();
	}
	//End reset table
	
	//Show message
	private void showMessage(String title, String message, int option) {
		JOptionPane.showMessageDialog(homePage.getHomePanel(), message, title, option);
		
	}
	
	private int showConfirm(String title, String message, int option) {
		return JOptionPane.showConfirmDialog(homePage.getHomePanel(), message, title, option);
	}
	//End show message
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		DefaultTableModel dfDrink = homePage.getDfDrinkTableModel();
		DefaultTableModel dfBill = homePage.getDfBillDefaultTableModel();
		if(o.equals(homePage.getBillJTable())) {
			homePage.getQuantityJTextField().setText(dfBill.getValueAt(homePage.getBillJTable().getSelectedRow(), 2).toString());
			homePage.getQuantityJTextField().setEditable(true);
			homePage.getQuantityJTextField().setFocusable(true);
			homePage.getPlusJButton().setEnabled(true);
			homePage.getMinusJButton().setEnabled(true);
			homePage.getDeleteJButton().setEnabled(true);
		}
		if(o.equals(homePage.getDrinkJTable())) {
			int id = Integer.parseInt(dfDrink.getValueAt(homePage.getDrinkJTable().getSelectedRow(), 0).toString().trim());
			Optional<Drink> drink = drinkRepository.findById(id);
			if(drink.isPresent()){
				homePage.getImageJLabel().setIcon(new ImageIcon(String.format(drink.get().getPathImage(), 200)));
				homePage.getNameJLabel().setText(drink.get().getName());
				homePage.getUnitPriceJLabel().setText("" + drink.get().getUnitPrice());
				homePage.getArrowLeftJButton().setEnabled(true);
				homePage.getArrowRightJButton().setEnabled(true);
				homePage.getAddJButton().setEnabled(true);
			}
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

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getDocument();
		if (source.equals(homePage.getSearchJTextField().getDocument())) {
			String name = homePage.getSearchJTextField().getText().trim();
			resetTableDrink();
			if (name.isBlank()) {
				homePage.loadTable();
			} else {
				homePage.loadTable(name);
			}
		} else {
			if (source.equals(homePage.getPayByEmployeeJTextField().getDocument())) {
				String payAmountString = homePage.getPayByEmployeeJTextField().getText().trim();
				double payAmount = 0;
				double result = 0.0;
				if (payAmountString.isBlank()) {
					result = 0.0;
				} else {
					if (!payAmountString.matches("^\\d+(\\.\\d+)?$")) {
						result = 0.0;
					} else {
						double totalPrice = getTotalPrice();
						payAmount = Double.parseDouble(payAmountString);
						if (payAmount > totalPrice) {
							result = payAmount - totalPrice;
						}
						homePage.getPayByEmployeeChangeValueJLabel().setText("" + result);
						updateTotal();
					}
				}
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getDocument();
		if (source.equals(homePage.getSearchJTextField().getDocument())) {
			String name = homePage.getSearchJTextField().getText().trim();
			resetTableDrink();
			if (name.isBlank()) {
				homePage.loadTable();
			} else {
				homePage.loadTable(name);
			}
		} else {
			if (source.equals(homePage.getPayByEmployeeJTextField().getDocument())) {
				String payAmountString = homePage.getPayByEmployeeJTextField().getText().trim();
				double payAmount = 0;
				double result = 0.0;
				if (payAmountString.isBlank()) {
					result = 0.0;
				} else {
					if (!payAmountString.matches("^\\d+(\\.\\d+)?$")) {
						result = 0.0;
					} else {
						double totalPrice = getTotalPrice();
						payAmount = Double.parseDouble(payAmountString);
						if (payAmount > totalPrice) {
							result = payAmount - totalPrice;
						}
						homePage.getPayByEmployeeChangeValueJLabel().setText("" + result);
						updateTotal();
					}
				}
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
