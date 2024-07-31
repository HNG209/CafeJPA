package com.project.SpringCafeUI.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.controller.HomePageController;
import com.project.SpringCafeUI.entity.Category;
import com.project.SpringCafeUI.entity.Drink;
import com.project.SpringCafeUI.repository.CategoryRepository;
import com.project.SpringCafeUI.repository.DrinkRepository;
import com.project.SpringCafeUI.utils.BorderRadius;
import com.project.SpringCafeUI.utils.FontSize;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class HomePage {
    //For drink table
    private JLabel searchJLabel, categoryJLabel;
    private JTextField searchJTextField;
    private JButton searchJButton, arrowLeftJButton, arrowRightJButton, addJButton;
    private JComboBox<String> categoryJComboBox;
    private DefaultComboBoxModel<String> dfCategoryComboBoxModel;
    //End for drink table

    //For bill table
    private JLabel totalDueJLabel, payByEmployeeJLabel,
            payByEmployeeChange, totalValueJLabel,
            payByEmployeeChangeValueJLabel,
            cardNumberJLabel, cardNumberValueJLabel;
    private JTextField payByEmployeeJTextField, quantityJTextField;
    private JButton plusJButton, minusJButton, deleteJButton, confirmJButton, dropJButton; //+, -, x
    private JTable drinkJTable, billJTable;
    private DefaultTableModel dfDrinkTableModel, dfBillDefaultTableModel;
    //End for bill table

    private JLabel imageJLabel, nameJLabel, unitPriceJLabel;
    private final HomePageController homePageController;
    private final Font font = FontSize.fontPlain16();
    private final Color color = Color.WHITE;

    private final JPanel homePanel;
    private final CategoryRepository categoryRepository;
    private final DrinkRepository drinkRepository;

    @Autowired
    public HomePage(@Lazy HomePageController homePageController, CategoryRepository categoryRepository, DrinkRepository drinkRepository) {
        this.homePageController = homePageController;
        this.categoryRepository = categoryRepository;
        this.drinkRepository = drinkRepository;
        homePanel = new JPanel();
        init();
    }

    @PostConstruct
    private void init(){
        this.setJPanel();
        this.setComponents();
        this.setLocations();
    }

    private void setComponents() {
        this.setJLabel();
        this.setJComboBox();
        this.setJTextField();
        this.setJButton();
        this.setJTable();
    }

    private void setLocations() {
        this.setNorth();
        this.setCenter();
        this.setWest();
        this.setEast();
    }

    private void setNorth() {
        JPanel panel = new JPanel();

        Box boxMain = Box.createVerticalBox();

        Box boxSearch = Box.createHorizontalBox();
        boxSearch.add(searchJLabel);
        boxSearch.add(Box.createHorizontalStrut(10));
        boxSearch.add(searchJTextField);
        boxSearch.add(Box.createHorizontalStrut(10));
        boxSearch.add(searchJButton);
        boxMain.add(boxSearch);
        boxMain.add(Box.createVerticalStrut(10));

        Box boxCategory = Box.createHorizontalBox();
        boxCategory.add(categoryJLabel);
        boxCategory.add(Box.createHorizontalStrut(10));
        boxCategory.add(categoryJComboBox);
        boxMain.add(boxCategory);
        boxMain.add(Box.createVerticalStrut(10));

        panel.add(boxMain);

        homePanel.add(panel, BorderLayout.NORTH);

    }

    private void setWest() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(260, 0));
		panel.setBorder(BorderFactory.createEtchedBorder());
        Box boxMain = Box.createVerticalBox();
        boxMain.add(Box.createVerticalStrut(10));
        boxMain.add(imageJLabel);
        boxMain.add(Box.createVerticalStrut(20));
        boxMain.add(nameJLabel);
        boxMain.add(Box.createVerticalStrut(20));
        boxMain.add(unitPriceJLabel);

        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(boxMain);

        homePanel.add(panel, BorderLayout.WEST);
    }

    private void setCenter() {
        Box boxMain = Box.createVerticalBox();
        Box boxTable = Box.createHorizontalBox();

        JScrollPane jScrollPane = new JScrollPane(drinkJTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        boxTable.add(jScrollPane);
        boxMain.add(boxTable);
        boxMain.add(Box.createVerticalStrut(20));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        panel.add(arrowLeftJButton);
        panel.add(addJButton);
        panel.add(arrowRightJButton);

        boxMain.add(panel);
        boxMain.setBorder(BorderFactory.createTitledBorder("Danh sách các món"));
        homePanel.add(boxMain, BorderLayout.CENTER);
    }

    private void setEast() {
        Box boxMain = Box.createVerticalBox();
        Box boxTable = Box.createHorizontalBox();
        JScrollPane jScrollPane = new JScrollPane(billJTable , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        boxTable.add(jScrollPane);
        boxMain.add(boxTable);
        boxMain.add(Box.createVerticalStrut(20));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panel.add(minusJButton);
        panel.add(quantityJTextField);
        panel.add(plusJButton);
        panel.add(deleteJButton);

        boxMain.add(panel);
        boxMain.add(Box.createVerticalStrut(20));

        JPanel panel2 = new JPanel(new GridLayout(5, 2));
        panel2.add(cardNumberJLabel);
        panel2.add(cardNumberValueJLabel);
        panel2.add(totalDueJLabel);
        panel2.add(totalValueJLabel);
        panel2.add(payByEmployeeJLabel);
        panel2.add(payByEmployeeJTextField);
        panel2.add(payByEmployeeChange);
        panel2.add(payByEmployeeChangeValueJLabel);
        panel2.add(confirmJButton);
        panel2.add(dropJButton);

        boxMain.add(panel2);

        boxMain.setBorder(BorderFactory.createTitledBorder("Danh sách món đặt"));
        homePanel.add(boxMain, BorderLayout.EAST);
    }

    private void setJLabel() {
        searchJLabel = new JLabel("Tên thức uống:");
        searchJLabel.setFont(font);

        categoryJLabel = new JLabel("Loại thức uống:");
        categoryJLabel.setFont(font);

        imageJLabel = new JLabel();
        imageJLabel.setIcon(new ImageIcon("icons\\icon-java_200px.png"));
        imageJLabel.setBorder(new BorderRadius.RoundedBorder(1, Color.GRAY));
        imageJLabel.setMaximumSize(new Dimension(200, 200));

        nameJLabel = new JLabel();
        nameJLabel.setFont(FontSize.fontPlain24());

        unitPriceJLabel = new JLabel();
        unitPriceJLabel.setFont(FontSize.fontBold30());

        totalDueJLabel = new JLabel("Thành tiền:");
        totalDueJLabel.setFont(font);

        totalValueJLabel = new JLabel("0.0");
        totalValueJLabel.setFont(font);

        payByEmployeeJLabel = new JLabel("Tiền khách đưa:");
        payByEmployeeJLabel.setFont(font);

        payByEmployeeChange = new JLabel("Tiền trả lại khách:");
        payByEmployeeChange.setFont(font);

        payByEmployeeChangeValueJLabel = new JLabel("0.0");
        payByEmployeeChangeValueJLabel.setFont(font);

        cardNumberJLabel = new JLabel("Số thẻ:");
        cardNumberJLabel.setFont(font);

        cardNumberValueJLabel = new JLabel();
        cardNumberValueJLabel.setFont(font);

        Dimension dimension = categoryJLabel.getPreferredSize();
        searchJLabel.setPreferredSize(dimension);
    }

    private void setJTextField() {
        searchJTextField = new JTextField(20);
        searchJTextField.setPreferredSize(categoryJComboBox.getPreferredSize());
        searchJTextField.getDocument().addDocumentListener(homePageController);
        searchJTextField.setFont(font);

        quantityJTextField = new JTextField();
        quantityJTextField.setPreferredSize(new Dimension(36, 24));
        quantityJTextField.setEditable(false);
        quantityJTextField.setFocusable(false);
        quantityJTextField.addActionListener(homePageController);

        payByEmployeeJTextField = new JTextField(10);
        payByEmployeeJTextField.getDocument().addDocumentListener(homePageController);
        payByEmployeeJTextField.setFont(font);
        payByEmployeeJTextField.setForeground(Color.RED);
        //payByEmployeeJTextField.setPreferredSize(new Dimension(126, 24));
    }

    private void setJButton() {
        searchJButton = new JButton("Tìm");
        searchJButton.setFont(font);
        searchJButton.setFocusPainted(false);
        searchJButton.setBackground(color);

        //For drink
        arrowLeftJButton = new JButton("<");
        arrowLeftJButton.setFont(font);
        arrowLeftJButton.setFocusPainted(false);
        arrowLeftJButton.setBackground(color);
        arrowLeftJButton.setEnabled(false);

        addJButton = new JButton("Thêm");
        addJButton.setFont(font);
        addJButton.setFocusPainted(false);
        addJButton.setBackground(color);
        addJButton.setEnabled(false);
        addJButton.addActionListener(homePageController);

        arrowRightJButton = new JButton(">");
        arrowRightJButton.setFont(font);
        arrowRightJButton.setFocusPainted(false);
        arrowRightJButton.setBackground(color);
        arrowRightJButton.setEnabled(false);
        //End for drink

        //For bill
        minusJButton = new JButton("-");
        minusJButton.setFont(font);
        minusJButton.setFocusPainted(false);
        minusJButton.setBackground(color);
        minusJButton.setEnabled(false);
        minusJButton.addActionListener(homePageController);

        plusJButton = new JButton("+");
        plusJButton.setFont(font);
        plusJButton.setFocusPainted(false);
        plusJButton.setBackground(color);
        plusJButton.setEnabled(false);
        plusJButton.addActionListener(homePageController);

        deleteJButton = new JButton("x");
        deleteJButton.setFont(font);
        deleteJButton.setFocusPainted(false);
        deleteJButton.setBackground(color);
        deleteJButton.setEnabled(false);
        deleteJButton.addActionListener(homePageController);

        confirmJButton = new JButton("Xác nhận thanh toán");
        confirmJButton.setFont(font);
        confirmJButton.setFocusable(false);
        confirmJButton.setBackground(color);
        confirmJButton.addActionListener(homePageController);

        dropJButton = new JButton("Hủy tất cả");
        dropJButton.setFont(font);
        dropJButton.setFocusable(false);
        dropJButton.setBackground(color);
        dropJButton.addActionListener(homePageController);
        //End for bill
    }

    private void setJTable() {
        String[] headerDrink = "Mã;Tên món;Loại;Đơn giá".split(";");
        dfDrinkTableModel = new DefaultTableModel(headerDrink, 0);
        drinkJTable = new JTable(dfDrinkTableModel);
        drinkJTable.setAutoCreateRowSorter(false);
        drinkJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        drinkJTable.addMouseListener(homePageController);
        drinkJTable.setFont(FontSize.fontPlain16());
        this.loadTable();

        String[] headerBill = "Tên món;Đơn giá;Số lượng".split(";");
        dfBillDefaultTableModel = new DefaultTableModel(headerBill, 0);
        billJTable = new JTable(dfBillDefaultTableModel);
        billJTable.setAutoCreateRowSorter(false);
        billJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        billJTable.setFont(FontSize.fontPlain16());
        billJTable.addMouseListener(homePageController);
    }

    private void setJComboBox() {
        dfCategoryComboBoxModel = new DefaultComboBoxModel<String>();
        categoryJComboBox = new JComboBox<String>(dfCategoryComboBoxModel);
        categoryJComboBox.setPreferredSize(new Dimension(320, 26));
        categoryJComboBox.setFont(font);
        categoryJComboBox.addItem("Tất cả");
        categoryJComboBox.addActionListener(homePageController);
        this.loadComboBoxCategory();

        categoryJComboBox.setBackground(color);
    }

    public void loadComboBoxCategory() {
        categoryRepository.findAll().forEach(
                category -> {
				this.getDfCategoryComboBoxModel().addElement(category.getName());
			}
        );
    }

    //Load one row table
    private void loadOneRow(Drink drink) {
        this.dfDrinkTableModel.addRow(new Object[] {
                drink.getId(),
                drink.getName(),
                drink.getCategory().getName(),
                drink.getUnitPrice(),
        });
    }
    //End load one row table

    public void loadTableByCategory(String value) {
        this.dfDrinkTableModel.setRowCount(0);
        Category category = categoryRepository.findByName(value).get(0);
        if(category != null){
            drinkRepository.findByCategory(category).forEach(this::loadOneRow);
        }
    }
    //End load Table

    //Load Table
    public void loadTable() {//refresh
        this.dfDrinkTableModel.setRowCount(0);
        drinkRepository.findAll().stream()
                .filter(drink -> !drink.isStatus())
                .forEach(this::loadOneRow);
    }

    public void loadTable(String name) {
        this.dfDrinkTableModel.setRowCount(0);
        drinkRepository.findByNameContaining(name).forEach(this::loadOneRow);
    }

    private void setJPanel() {
        homePanel.setLayout(new BorderLayout());
    }

}
