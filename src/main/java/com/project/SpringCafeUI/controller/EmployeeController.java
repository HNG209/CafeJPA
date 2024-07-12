package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.entity.Employee;
import com.project.SpringCafeUI.utils.TextProcessing;
import com.project.SpringCafeUI.view.EmployeePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EmployeeController implements ActionListener, MouseListener, DocumentListener {

	private final EmployeePage employeePage;

	@Autowired
	public EmployeeController(@Lazy EmployeePage employeePage) {
        this.employeePage = employeePage;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(employeePage.getAddJButton())) {
			addEmployee();
		} else if (source.equals(employeePage.getUpdateJButton())) {
			updateEmployee();
		} else {
			if (source.equals(employeePage.getDeleteJButton())) {
				deleteField();
			}
		}
	}
	
	private void addEmployee() {
		Employee employee = this.getEmployee();
//		if (employee != null) {
//			EmployeeDAO employeeDAO = new EmployeeDAO();
//			employeeDAO.addEmployee(employee);
//			showMessage("Thông báo", "Thêm thành công", JOptionPane.PLAIN_MESSAGE);
//			resetTable();
//			loadTable();
//		}
	}
	
	private void updateEmployee() {
		int row = employeePage.getTable().getSelectedRow();
		if (row < 0) {
			showConfirm("Lưu ý", "Vui lòng chọn một nhân viên để cập nhật", JOptionPane.PLAIN_MESSAGE);
		} else {
			int option = showConfirm("Thông báo", "Bạn chắc chắn muốn cập nhật?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				Employee employee = this.getEmployee();
				if (employee != null) {
//					EmployeeDAO employeeDAO = new EmployeeDAO();
//					boolean check = employeeDAO.updateEmployee(employee);
//					if (check) {
//						showMessage("Thông báo", "Cập nhật thành công", JOptionPane.PLAIN_MESSAGE);
//						resetTable();
//						loadTable();
//					} else {
//						showMessage("Thông báo", "Cập nhật thất bại", JOptionPane.PLAIN_MESSAGE);
//					}
				}
			}
		}
	}
	
	private void deleteField() {
		employeePage.getIdJTextField().setText("");
		employeePage.getNameJTextField().setText("");
		employeePage.getAgeJTextField().setText("");
		employeePage.getSexJComboBox().setSelectedIndex(0);;
		employeePage.getTitleJComboBox().setSelectedIndex(0);;
		employeePage.getSalaryJTextField().setText("");
		employeePage.getStartDateJTextField().setText("");
		employeePage.getStatusJComboBox().setSelectedIndex(0);
		employeePage.getDfTableModel();
		employeePage.getTable().clearSelection();
		
	}
	
	private void searchEmployee() {
		resetTable();
		String searchText = employeePage.getSearchJTextField().getText().trim();
		if (searchText.isBlank()) {
			loadTable();
		} else {
			loadTable(searchText);
		}
	}
	//Tạo một nhân viên
	
	private Employee getEmployee() {
		String idString = employeePage.getIdJTextField().getText().trim();
		int id = 0;
		String name = employeePage.getNameJTextField().getText().trim();
		String ageString = employeePage.getAgeJTextField().getText().trim();
		int age = 0;
		String sexString = employeePage.getSexDfComboBoxModel().getSelectedItem().toString().trim();
		boolean sex = false;
		String title = employeePage.getTitleDfComboBoxModel().getSelectedItem().toString().trim();
		String salaryString = employeePage.getSalaryJTextField().getText().trim();
		double salary = 0;
		String startDate = employeePage.getStartDateJTextField().getText().trim();
		Date date = Date.valueOf(startDate);
		String statusString = employeePage.getStatusDfComboBoxModel().getSelectedItem().toString().trim();
		boolean status = false;
		
		if (!idString.isBlank()) {
			id = Integer.parseInt(idString);
		}
		
		if (startDate.isBlank()) {
			date = Date.valueOf(LocalDate.now());
		}
		
		if (name.isBlank()) {
			showMessage("Lưu ý", "Tên không được rỗng", JOptionPane.PLAIN_MESSAGE);
			employeePage.getNameJTextField().requestFocus();
			employeePage.getNameJTextField().selectAll();
			return null;
		}
		
		if (ageString.isBlank()) {
			showMessage("Lưu ý", "Tuổi không được rỗng", JOptionPane.PLAIN_MESSAGE);
			employeePage.getAgeJTextField().requestFocus();
			employeePage.getAgeJTextField().selectAll();
			return null;
		} else {
			if (!ageString.matches("1[8-9]|[2-5][0-9]|60")) {
				showMessage("Lưu ý", "Tuổi nhân viên nằm trong khoảng từ 18 đến 60", JOptionPane.PLAIN_MESSAGE);
				employeePage.getAgeJTextField().requestFocus();
				employeePage.getAgeJTextField().selectAll();
				return null;
			} else {
				age = Integer.parseInt(ageString);
			}
		}
		
		if (salaryString.isBlank()) {
			showMessage("Lưu ý", "Lương không được rỗng", JOptionPane.PLAIN_MESSAGE);
			employeePage.getSalaryJTextField().requestFocus();
			employeePage.getSalaryJTextField().selectAll();
			return null;
		} else {
			if (!salaryString.matches("^\\d+(\\.\\d+)?$")) {
				showMessage("Lưu ý", "Tiền lương không hợp lệ", JOptionPane.PLAIN_MESSAGE);
				employeePage.getSalaryJTextField().requestFocus();
				employeePage.getSalaryJTextField().selectAll();
				return null;
			} else {
				salary = Double.parseDouble(salaryString);
			}
		}
		
		sex = sexString.equalsIgnoreCase("Nữ");
		status = statusString.equalsIgnoreCase("Đang làm việc");
		
		return new Employee(id, name, age, sex, title, salary, date, status);
	}
	
	//End tạo một nhân viên
	
	//Reset table
	private void resetTable() {
		employeePage.getDfTableModel().setRowCount(0);
		employeePage.getTable().repaint();
	}
	//End reset table
	
	//Load one row table
	private void loadOneRow(DefaultTableModel dfTableModel, Employee employee) {
		DecimalFormat formater = new DecimalFormat("#.0");
		dfTableModel.addRow(new Object[] {
			employee.getId(),
			employee.getName(),
			employee.getAge(),
			employee.isSex() ? "Nữ" : "Nam",
			employee.getTitle(),
			formater.format(employee.getSalary()),
			employee.getStartDate(),
			employee.isStatus() ? "Đang làm việc" : "Ngưng làm việc"
		});
	}
	//End load one row table
	
	//Load table
	public void loadTable() {
//		EmployeeDAO employeeDAO = new EmployeeDAO();
//		employeeDAO.getEmployees().stream()
//			.forEach(employee -> loadOneRow(
//				employeePage.getDfTableModel(), employee)
//			);
	}
	
	private void loadTable(String name) {
//		EmployeeDAO employeeDAO = new EmployeeDAO();
//
//		employeeDAO.getEmployees().stream()
//			.filter(employee -> TextProcessing.formatString(employee.getName()).contains(TextProcessing.formatString(name)))
//			.forEach(employee -> loadOneRow(
//				employeePage.getDfTableModel(), employee)
//		);
	}
	//End load table
	
	//Show message
	private void showMessage(String title, String message, int option) {
		JOptionPane.showMessageDialog(employeePage.getEmployeePanel(), message, title, option);
		
	}
	
	private int showConfirm(String title, String message, int option) {
		return JOptionPane.showConfirmDialog(employeePage.getEmployeePanel(), message, title, option);
	}
	//End show message
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = employeePage.getTable().getSelectedRow();
		DefaultTableModel df = employeePage.getDfTableModel();
		employeePage.getIdJTextField().setText(df.getValueAt(row, 0).toString());
		employeePage.getNameJTextField().setText(df.getValueAt(row, 1).toString());
		employeePage.getAgeJTextField().setText(df.getValueAt(row, 2).toString());
		employeePage.getSexDfComboBoxModel().setSelectedItem(df.getValueAt(row, 3));
		employeePage.getTitleDfComboBoxModel().setSelectedItem(df.getValueAt(row, 4));
		employeePage.getSalaryJTextField().setText(df.getValueAt(row, 5).toString());
		employeePage.getStartDateJTextField().setText(df.getValueAt(row, 6).toString());
		employeePage.getStatusDfComboBoxModel().setSelectedItem(df.getValueAt(row, 7).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
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
		searchEmployee();
		
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		searchEmployee();
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
