package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.entity.Category;
import com.project.SpringCafeUI.entity.Drink;
import com.project.SpringCafeUI.utils.ImageDisplay;
import com.project.SpringCafeUI.utils.TextProcessing;
import com.project.SpringCafeUI.view.HomePage;
import com.project.SpringCafeUI.view.ProductPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ProductPageController implements ActionListener, MouseListener, DocumentListener {
	private final ProductPage productPage;
	private HomePage homePage;

	@Autowired
	public ProductPageController(@Lazy ProductPage productPage) {
        this.productPage = productPage;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object soure = e.getSource();
		if (soure.equals(productPage.getAddJButton())) {
			addDrink();
		} else if (soure.equals(productPage.getUpdateJButton())) {
			updateDrink();
		} else if (soure.equals(productPage.getAddCategoryJButton())) {
			addCategory();
		} else if (soure.equals(productPage.getSearchJButton())
				|| soure.equals(productPage.getNameSubJTextField())){
			searchDrink();
		} else {
			if (soure.equals(productPage.getCategorySubJComboBox())
					|| soure.equals(productPage.getStatusSubJComboBox())) {
				filterTable();
			}
		}
	}
	// Add drink
	private void addDrink() {
		Drink drink = this.getValueField();
		
//		if (drink != null) {
//			String path = ImageDisplay.getPath(productPage);
//			productPage.getImageJLabel().setIcon(new ImageIcon(path));
//			drink.setPathImage(ImageDisplay.formatPath(path));
//
//			DrinkDAO drinkDAO = new DrinkDAO();
//			boolean check = drinkDAO.addDrink(drink);
//
//			if (check) {
//				showMessage("Thông báo", "Thêm thành công", JOptionPane.PLAIN_MESSAGE);
//				resetTable();
//				loadTable();
//
//				homePage = HomePage.getInstance();
//				homePage.getHomePageController().resetTableDrink();
//				homePage.getHomePageController().loadTable();
//			} else {
//				showMessage("Thông báo", "Thêm thất bại", JOptionPane.PLAIN_MESSAGE);
//			}
//		}
	}
	//End add drink
	
	//Update drink
	private void updateDrink() {
		int row = productPage.getTable().getSelectedRow();
		if (row < 0) {
			showMessage("Lưu ý", "Vui lòng chọn một thức uống để cập nhật", JOptionPane.PLAIN_MESSAGE);
		} else {
			int option = showConfirm("Thông báo", "Bạn có chắc muốn cập nhật?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				Drink drink = this.getValueField();
				if (drink != null) {
					int option1 = showConfirm("Thông báo", "Bạn có muốn đổi ảnh?", JOptionPane.YES_NO_OPTION);
					if (option1 == JOptionPane.YES_OPTION) {
						String path = ImageDisplay.getPath(productPage.getProductPanel());
						productPage.getImageJLabel().setIcon(new ImageIcon(path));
						drink.setPathImage(ImageDisplay.formatPath(path));
					}
					
//					DrinkDAO drinkDAO = new DrinkDAO();
//					boolean check = drinkDAO.updateDrink(drink);
//
//					if (check) {
//						showMessage("Thông báo", "Cập nhật thành công", JOptionPane.PLAIN_MESSAGE);
//						resetTable();
//						loadTable();
//
//						homePage = HomePage.getInstance();
//						homePage.getHomePageController().resetTableDrink();
//						homePage.getHomePageController().loadTable();
//					} else {
//						showMessage("Thông báo", "Cập nhật thất bại", JOptionPane.PLAIN_MESSAGE);
//					}
				}
			}
		}
	}
	//End update drink
	
	//Add category
	private void addCategory() {
//		String value = productPage.getDfCategoryJComboBoxModel().getSelectedItem().toString().trim();
//		CategoryDAO categoryDAO = new CategoryDAO();
//		Category category = new Category(0, value);
//		List<Category> list = categoryDAO.getCategories();
//		if (list.contains(category)) {
//			showMessage("Thông báo", "Loại sản phẩm đã tồn tại", JOptionPane.PLAIN_MESSAGE);
//		} else {
//			categoryDAO.addCategory(category);
//			productPage.getDfCategoryJComboBoxModel().addElement(category.getName());
//			productPage.getDfCategorySubJComboBoxModel().addElement(category.getName());
//			showMessage("Thông báo", "Thêm loại sản phẩm thành công", JOptionPane.PLAIN_MESSAGE);
//		}
	}
	//End add category
	
	//Search drink
	private void searchDrink() {
		resetTable();
		String searchText = productPage.getNameSubJTextField().getText().trim();
		if (searchText.isBlank()) {
			loadTable();
		} else {
			loadTable(searchText);
		}
	}
	//End search drink
	
	//Filter table
	private void filterTable() {
		String categoryOption = productPage.getDfCategorySubJComboBoxModel().getSelectedItem().toString();
        String statusOption = productPage.getDfStatusSubJBoxModel().getSelectedItem().toString();
        
        if (categoryOption.equalsIgnoreCase("Tất cả") 
        		&& statusOption.equalsIgnoreCase("Tất cả")) {
			resetTable();
			loadTable();
		} else if (categoryOption.equalsIgnoreCase("Tất cả")) {
			resetTable();
			loadTableByStatus(statusOption);
		} else if (statusOption.equalsIgnoreCase("Tất cả")) {
			resetTable();
			loadTableByCategory(categoryOption);
		} else {
			resetTable();
			loadTableByStatusAndCategory(categoryOption, statusOption);
		}
    }
	//End filter table
	
	//Load comboBox category
	public void loadComboBoxCategory() {
//		CategoryDAO categoryDAO = new CategoryDAO();
//		categoryDAO.getCategories().stream()
//			.forEach(category -> {
//				productPage.getDfCategoryJComboBoxModel().addElement(category.getName());
//				productPage.getDfCategorySubJComboBoxModel().addElement(category.getName());
//			}
//		);
	}
	//End load comboBox category
	
	//Load one row table
	private void loadOneRow(DefaultTableModel dfTableModel, Drink drink) {
		dfTableModel.addRow(new Object[] {
			drink.getId(),
			drink.getName(),
			drink.getCategory().getName(),
			drink.getUnitPrice(),
			drink.isStatus() ? "Ngừng bán" : "Đang bán"
		});
	}
	//End load one row table
	
	//Load Table
	public void loadTable() {
//		DrinkDAO drinkDAO = new DrinkDAO();
//		drinkDAO.getDrinks().stream()
//			.forEach(drink -> loadOneRow(
//				productPage.getDfDefaultTableModel(), drink)
//			);
//		((DefaultTableModel)productPage.getTable().getModel()).fireTableDataChanged();
	}
	
	private void loadTable(String name) {
//		DrinkDAO drinkDAO = new DrinkDAO();
//
//		drinkDAO.getDrinks().stream()
//			.filter(drink -> TextProcessing.formatString(drink.getName()).contains(TextProcessing.formatString(name)))
//			.forEach(drink -> loadOneRow(
//				productPage.getDfDefaultTableModel(), drink)
//		);
	}
	

	private void loadTableByCategory(String value) {
//		DrinkDAO drinkDAO = new DrinkDAO();
//
//		drinkDAO.getDrinks().stream()
//			.filter(drink -> drink.getCategory().getName().equalsIgnoreCase(value))
//			.forEach(drink -> loadOneRow(
//				productPage.getDfDefaultTableModel(), drink)
//		);
	}
	
	private void loadTableByStatus(String value) {
//		DrinkDAO drinkDAO = new DrinkDAO();
//
//		for(Drink drink : drinkDAO.getDrinks()) {
//			String status = drink.isStatus() ? "Ngừng bán" : "Đang bán";
//			if (status.equalsIgnoreCase(value)) {
//				loadOneRow(productPage.getDfDefaultTableModel(), drink);
//			}
//		}
	}
	
	private void loadTableByStatusAndCategory(String category, String status) {
//		DrinkDAO drinkDAO = new DrinkDAO();
//		for(Drink drink : drinkDAO.getDrinks()) {
//			String value = drink.isStatus() ? "Ngừng bán" : "Đang bán";
//			if (status.equalsIgnoreCase(value) && category.equalsIgnoreCase(drink.getCategory().getName())) {
//				loadOneRow(productPage.getDfDefaultTableModel(), drink);
//			}
//		}
		
	}
	
	//End load Table
	
	//Reset table
	private void resetTable() {
		productPage.getDfDefaultTableModel().setRowCount(0);
		productPage.getTable().repaint();
	}
	//End reset table
	
	//Get value field
	private Drink getValueField() {
//		String idString = productPage.getIdJTextField().getText().trim();
//		int id = 0;
//		if (!idString.isBlank()) {
//			id = Integer.parseInt(idString);
//		}
//
//		String name = productPage.getNameJTextField().getText().trim();
//		if (name.isBlank()) {
//			showMessage("Thông báo", "Tên thức uống không được rỗng", JOptionPane.PLAIN_MESSAGE);
//			productPage.getNameJTextField().setFocusable(true);
//			productPage.getNameJTextField().selectAll();
//			return null;
//		}
//		String categtoryString = productPage.getDfCategoryJComboBoxModel().getSelectedItem().toString();
//		Category category = new CategoryDAO().getCategory("name", categtoryString);
//		String unitPriceString = productPage.getUnitPriceJTextField().getText().trim();
//		double unitPrice = 0;
//		if (unitPriceString.isBlank()) {
//			showMessage("Thông báo", "Đơn giá không được rỗng", JOptionPane.PLAIN_MESSAGE);
//			productPage.getUnitPriceJTextField().setFocusable(true);
//			productPage.getUnitPriceJTextField().selectAll();
//			return null;
//		} else if (!unitPriceString.matches("^\\d+(\\.\\d+)?$")) {
//			showMessage("Thông báo", "Đơn giá không đúng định dạng", JOptionPane.PLAIN_MESSAGE);
//			productPage.getUnitPriceJTextField().setFocusable(true);
//			productPage.getUnitPriceJTextField().selectAll();
//			return null;
//		} else {
//			unitPrice = Double.parseDouble(unitPriceString);
//		}
//		String note = productPage.getNoteJTextArea().getText().trim();
//		String description = productPage.getDescribeJTextArea().getText().trim();
//		String statusString = productPage.getDfStatusJBoxModel().getSelectedItem().toString();
//		boolean status = statusString.equalsIgnoreCase("Đang bán") ? false : true;
//
//		String path = "";
//
//		Drink drink = new DrinkDAO().getDrink("id", id);
//
//		if (drink != null) {
//			path = drink.getPathImage();
//		}
//
//		return new Drink(id, name, category, unitPrice, note, description, status, path);
		return new Drink();
	}
	//End get value field
	
	//Show message
	private void showMessage(String title, String message, int option) {
		JOptionPane.showMessageDialog(productPage.getProductPanel(), message, title, option);
		
	}
	
	private int showConfirm(String title, String message, int option) {
		return JOptionPane.showConfirmDialog(productPage.getProductPanel(), message, title, option);
	}
	//End show message
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = productPage.getTable().getSelectedRow();
		DefaultTableModel df = productPage.getDfDefaultTableModel();
		
//		DrinkDAO drinkDAO = new DrinkDAO();
//		Drink drink = drinkDAO.getDrink("id" ,Integer.parseInt(df.getValueAt(row, 0).toString().trim()));
//
//		if (drink != null) {
//			productPage.getIdJTextField().setText(df.getValueAt(row, 0).toString());
//			productPage.getNameJTextField().setText(df.getValueAt(row, 1).toString());
//			productPage.getDfCategoryJComboBoxModel().setSelectedItem(df.getValueAt(row, 2).toString());
//			productPage.getUnitPriceJTextField().setText(df.getValueAt(row, 3).toString());
//			productPage.getDescribeJTextArea().setText(drink.getDescription());
//			productPage.getNoteJTextArea().setText(drink.getNote());
//			productPage.getDfStatusJBoxModel().setSelectedItem(df.getValueAt(row, 4));
//			productPage.getImageJLabel().setIcon(new ImageIcon(String.format(drink.getPathImage(), 100)));
//		}
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
		searchDrink();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		searchDrink();
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		searchDrink();
		
	}
	
	
}
