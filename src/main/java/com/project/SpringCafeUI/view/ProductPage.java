package com.project.SpringCafeUI.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Objects;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.project.SpringCafeUI.controller.ProductPageController;
import com.project.SpringCafeUI.entity.Category;
import com.project.SpringCafeUI.entity.Drink;
import com.project.SpringCafeUI.repository.CategoryRepository;
import com.project.SpringCafeUI.repository.DrinkRepository;
import com.project.SpringCafeUI.utils.BorderRadius;
import com.project.SpringCafeUI.utils.FontSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ProductPage {
    private JLabel
            imageJLabel,
            idJLabel,
            nameJLabel,
            categoryJLabel,
            unitPriceJLabel,
            describeJLabel,
            noteJLabel,
            statusJLabel,
            nameSubJLabel,
            categorySubJLabel,
            statusSubJLabel;
    private JTextField idJTextField,
            nameJTextField,
            unitPriceJTextField,
            nameSubJTextField;
    private JTextArea describeJTextArea, noteJTextArea;

    private JComboBox<String> categoryJComboBox , statusJComboBox, categorySubJComboBox, statusSubJComboBox;
    private DefaultComboBoxModel<String> dfCategoryJComboBoxModel,
            dfStatusJBoxModel,
            dfCategorySubJComboBoxModel,
            dfStatusSubJBoxModel;
    private JButton addCategoryJButton,
            addJButton,
            updateJButton,
            searchJButton;
    private JTable table;
    private DefaultTableModel dfDefaultTableModel;
    private final ProductPageController productPageController;
    private final Font font = FontSize.fontPlain16();

    private final CategoryRepository categoryRepository;
    private final DrinkRepository drinkRepository;
    private final JPanel productPanel;

//    private static ProductPage INSTANCE = new ProductPage();
//
//    public static ProductPage getInstace() {
//        return INSTANCE;
//    }

    @Autowired
    public ProductPage(@Lazy ProductPageController productPageController, CategoryRepository categoryRepository, DrinkRepository drinkRepository) {
        this.productPageController = productPageController;
        this.categoryRepository = categoryRepository;
        this.drinkRepository = drinkRepository;
        productPanel = new JPanel();
        this.setPanel();
        this.setComponents();
        this.setLocations();
    }

    private void setLocations() {
        this.setCenter();
    }

    private void setComponents() {
        this.setJLabel();
        this.setJCombobox();
        this.setJTextField();
        this.setJTextArea();
        this.setJButton();
        this.setJTable();
    }

    private void setJLabel() {
        Font fontSize = FontSize.fontBold16();
        imageJLabel = new JLabel();
        imageJLabel.setIcon(new ImageIcon("icons\\icon-java_100px.png"));
        imageJLabel.setBorder(new BorderRadius.RoundedBorder(1, Color.GRAY));
        imageJLabel.setMaximumSize(new Dimension(100, 100));

        idJLabel = new JLabel("Mã sản phẩm:");
        idJLabel.setFont(fontSize);

        nameJLabel = new JLabel("Tên sản phẩm:");
        nameJLabel.setFont(fontSize);

        categoryJLabel = new JLabel("Loại sản phẩm:");
        categoryJLabel.setFont(fontSize);

        unitPriceJLabel = new JLabel("Đơn giá:");
        unitPriceJLabel.setFont(fontSize);

        describeJLabel = new JLabel("Mô tả:");
        describeJLabel.setFont(fontSize);

        noteJLabel = new JLabel("Ghi chú:");
        noteJLabel.setFont(fontSize);

        statusJLabel = new JLabel("Trạng thái:");
        statusJLabel.setFont(fontSize);

        nameSubJLabel = new JLabel("Tên sản phẩm");
        nameSubJLabel.setFont(fontSize);

        categorySubJLabel = new JLabel("Loại sản phẩm");
        categorySubJLabel.setFont(fontSize);

        statusSubJLabel = new JLabel("Trạng thái");
        statusSubJLabel.setFont(fontSize);

        Dimension sizeDimension = categoryJLabel.getPreferredSize();
        idJLabel.setPreferredSize(sizeDimension);
        nameJLabel.setPreferredSize(sizeDimension);
        categoryJLabel.setPreferredSize(sizeDimension);
        unitPriceJLabel.setPreferredSize(sizeDimension);
        describeJLabel.setPreferredSize(sizeDimension);
        noteJLabel.setPreferredSize(sizeDimension);
        statusJLabel.setPreferredSize(sizeDimension);
    }

    private void setJTextField() {
        idJTextField = new JTextField(30);
        idJTextField.setEditable(false);
        idJTextField.setFocusable(false);
        idJTextField.setFont(font);

        nameJTextField = new JTextField(30);
        nameJTextField.setFont(font);

        unitPriceJTextField = new JTextField(30);
        unitPriceJTextField.setFont(font);

        nameSubJTextField = new JTextField(20);
        nameSubJTextField.getDocument().addDocumentListener(productPageController);;
        nameSubJTextField.setFont(font);

        Dimension sizeDimension = statusJComboBox.getPreferredSize();
        idJTextField.setPreferredSize(sizeDimension);
        nameJTextField.setPreferredSize(sizeDimension);
        unitPriceJTextField.setPreferredSize(sizeDimension);
    }
    private void setJTextArea() {
        Border borderRadius = new BorderRadius.RoundedBorder(1, Color.GRAY);
        describeJTextArea = new JTextArea(5, 30);
        describeJTextArea.setFont(font);
        describeJTextArea.setBorder(borderRadius);
        noteJTextArea = new JTextArea(5, 20);
        noteJTextArea.setFont(font);
        noteJTextArea.setBorder(borderRadius);

    }

    private void setJButton() {
        addCategoryJButton = new JButton("+");
        addCategoryJButton.setFont(FontSize.fontBold16());
        addCategoryJButton.setBackground(Color.WHITE);
        addCategoryJButton.setFocusPainted(false);

        addJButton = new JButton("Thêm");
        addJButton.setFont(FontSize.fontBold24());
        addJButton.setFocusPainted(false);
        addJButton.setBackground(Color.WHITE);
        addJButton.addActionListener(productPageController);

        updateJButton = new JButton("Cập nhật");
        updateJButton.setFont(FontSize.fontBold24());
        updateJButton.setFocusPainted(false);
        updateJButton.setBackground(Color.WHITE);
        updateJButton.addActionListener(productPageController);

        searchJButton = new JButton("Tìm");
        searchJButton.setFont(FontSize.fontBold16());
        searchJButton.setBackground(Color.WHITE);
        searchJButton.setFocusPainted(false);

        Dimension sizeDimension = new Dimension(164, 64);
        addJButton.setMaximumSize(sizeDimension);
        updateJButton.setMaximumSize(sizeDimension);

        addCategoryJButton.addActionListener(productPageController);
    }

    private void setJCombobox() {
        dfCategoryJComboBoxModel = new DefaultComboBoxModel<String>();
        categoryJComboBox = new JComboBox<String>();
        categoryJComboBox.setModel(dfCategoryJComboBoxModel);
        categoryJComboBox.setEditable(true);
        categoryJComboBox.setFont(font);
        categoryJComboBox.setBackground(Color.WHITE);

        dfStatusJBoxModel = new DefaultComboBoxModel<String>();
        statusJComboBox = new JComboBox<String>();
        statusJComboBox.setModel(dfStatusJBoxModel);
        statusJComboBox.addItem("Đang bán");
        statusJComboBox.addItem("Ngừng bán");
        statusJComboBox.setFont(font);
        statusJComboBox.setBackground(Color.WHITE);

        dfCategorySubJComboBoxModel = new DefaultComboBoxModel<String>();
        categorySubJComboBox = new JComboBox<String>();
        categorySubJComboBox.setModel(dfCategorySubJComboBoxModel);
        categorySubJComboBox.addItem("Tất cả");
        categorySubJComboBox.addActionListener(productPageController);
        categorySubJComboBox.setBackground(Color.WHITE);

        dfStatusSubJBoxModel = new DefaultComboBoxModel<String>();
        statusSubJComboBox = new JComboBox<String>();
        statusSubJComboBox.setModel(dfStatusSubJBoxModel);
        statusSubJComboBox.addItem("Tất cả");
        statusSubJComboBox.addItem("Đang bán");
        statusSubJComboBox.addItem("Ngừng bán");
        statusSubJComboBox.setBackground(Color.WHITE);
        statusSubJComboBox.addActionListener(productPageController);

        this.loadComboBoxCategory();
    }

    public void loadComboBoxCategory() {
        categoryRepository.findAll().
                forEach(category -> {
                    this.getDfCategoryJComboBoxModel().addElement(category.getName());
                    this.getDfCategorySubJComboBoxModel().addElement(category.getName());
                });
    }

    private void loadOneRow(Drink drink) {
        getDfDefaultTableModel().addRow(new Object[] {
                drink.getId(),
                drink.getName(),
                drink.getCategory().getName(),
                drink.getUnitPrice(),
                drink.isStatus() ? "Ngừng bán" : "Đang bán"
        });
    }

    public void loadTable() {
        getDfDefaultTableModel().setRowCount(0);
        drinkRepository.findAll().forEach(this::loadOneRow);
    }

    public void loadTable(String name) {
        getDfDefaultTableModel().setRowCount(0);
        drinkRepository.findByNameContaining(name).forEach(this::loadOneRow);
    }

    public void loadTableByCategory(String value) {
        getDfDefaultTableModel().setRowCount(0);
        Category tmp = categoryRepository.findByName(value).get(0);
        if(tmp != null){
            drinkRepository.findByCategory(tmp).forEach(this::loadOneRow);
        }
    }

    public void loadTableByStatus(String value) {
        getDfDefaultTableModel().setRowCount(0);
        drinkRepository.findByStatus(Objects.equals(value, "Ngừng bán")).forEach(this::loadOneRow);
    }

    public void loadTextfield(int row){
        Optional<Drink> drink = drinkRepository.findById(Integer.parseInt(getDfDefaultTableModel().getValueAt(row, 0).toString()));
        if(drink.isPresent()){
            DefaultTableModel model = getDfDefaultTableModel();
            this.getIdJTextField().setText(model.getValueAt(row, 0).toString());
            this.getNameJTextField().setText(model.getValueAt(row, 1).toString());
            this.getDfCategoryJComboBoxModel().setSelectedItem(model.getValueAt(row, 2).toString());
            this.getUnitPriceJTextField().setText(model.getValueAt(row, 3).toString());
            this.getDescribeJTextArea().setText(drink.get().getDescription());
            this.getNoteJTextArea().setText(drink.get().getNote());
            this.getDfStatusJBoxModel().setSelectedItem(model.getValueAt(row, 4));
            this.getImageJLabel().setIcon(new ImageIcon(String.format(drink.get().getPathImage(), 100)));
        }
    }

    private void setJTable() {
        String[] headers = "Mã SP;Tên SP;Loại SP;Đơn giá;Trạng thái".split(";");
        dfDefaultTableModel = new DefaultTableModel(headers, 0);
        table = new JTable(dfDefaultTableModel);
        table.setAutoCreateRowSorter(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFont(FontSize.fontPlain16());
        this.loadTable();

        table.addMouseListener(productPageController);
    }

    private void setCenter() {
        Box boxMain = Box.createHorizontalBox();

        Box boxLeft, boxRight;

        //Left
        boxLeft = Box.createVerticalBox();
        boxLeft.add(Box.createVerticalStrut(10));
        JPanel panelImage = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelImage.add(imageJLabel);

        Box imgBox, idBox, nameBox, categoryBox, unitPriceBox, describeBox, noteBox, statusBox, buttonBox;
        imgBox = Box.createHorizontalBox();
        imgBox.add(panelImage);
        boxLeft.add(imgBox);
        boxLeft.add(Box.createVerticalStrut(20));
        idBox = Box.createHorizontalBox();
        idBox.add(Box.createHorizontalStrut(10));
        idBox.add(idJLabel);
        idBox.add(Box.createHorizontalStrut(30));
        idBox.add(idJTextField);
        idBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(idBox);
        boxLeft.add(Box.createVerticalStrut(20));

        nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalStrut(10));
        nameBox.add(nameJLabel);
        nameBox.add(Box.createHorizontalStrut(30));
        nameBox.add(nameJTextField);
        nameBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(nameBox);
        boxLeft.add(Box.createVerticalStrut(20));

        categoryBox = Box.createHorizontalBox();
        categoryBox.add(Box.createHorizontalStrut(10));
        categoryBox.add(categoryJLabel);
        categoryBox.add(Box.createHorizontalStrut(30));
        categoryBox.add(categoryJComboBox);

        categoryBox.add(Box.createHorizontalStrut(10));
        categoryBox.add(addCategoryJButton);
        categoryBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(categoryBox);
        boxLeft.add(Box.createVerticalStrut(20));

        unitPriceBox = Box.createHorizontalBox();
        unitPriceBox.add(Box.createHorizontalStrut(10));
        unitPriceBox.add(unitPriceJLabel);
        unitPriceBox.add(Box.createHorizontalStrut(30));
        unitPriceBox.add(unitPriceJTextField);
        unitPriceBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(unitPriceBox);
        boxLeft.add(Box.createVerticalStrut(20));

        describeBox = Box.createHorizontalBox();
        describeBox.add(Box.createHorizontalStrut(10));
        describeBox.add(describeJLabel);
        describeBox.add(Box.createHorizontalStrut(30));
        describeBox.add(describeJTextArea);
        describeBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(describeBox);
        boxLeft.add(Box.createVerticalStrut(20));

        noteBox = Box.createHorizontalBox();
        noteBox.add(Box.createHorizontalStrut(10));
        noteBox.add(noteJLabel);
        noteBox.add(Box.createHorizontalStrut(30));
        noteBox.add(noteJTextArea);
        noteBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(noteBox);
        boxLeft.add(Box.createVerticalStrut(20));

        statusBox = Box.createHorizontalBox();
        statusBox.add(Box.createHorizontalStrut(10));
        statusBox.add(statusJLabel);
        statusBox.add(Box.createHorizontalStrut(30));
        statusBox.add(statusJComboBox);
        statusBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(statusBox);
        boxLeft.add(Box.createVerticalStrut(20));

        buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalStrut(158));
        buttonBox.add(addJButton);
        buttonBox.add(Box.createHorizontalStrut(10));
        buttonBox.add(updateJButton);
        buttonBox.add(Box.createHorizontalStrut(10));
        boxLeft.add(buttonBox);
        boxLeft.add(Box.createVerticalStrut(20));

        boxMain.add(boxLeft);
        boxMain.add(Box.createHorizontalStrut(1));
        //End left
        //Right
        boxRight = Box.createVerticalBox();
        boxRight.add(Box.createVerticalStrut(114));

        Box boxTitle = Box.createHorizontalBox();
        Box nameSubBox, categorySubBox, statusSubBox;
        Box searchBox;
        nameSubBox = Box.createVerticalBox();
        nameSubBox.add(nameSubJLabel);
        nameSubBox.add(Box.createVerticalStrut(10));
        nameSubBox.add(nameSubJTextField);
        searchBox = Box.createHorizontalBox();
        searchBox.add(nameSubJTextField);
        searchBox.add(Box.createHorizontalStrut(5));
        searchBox.add(searchJButton);
        nameSubBox.add(searchBox);
        boxTitle.add(nameSubBox);
        boxTitle.add(Box.createHorizontalStrut(10));

        categorySubBox = Box.createVerticalBox();
        categorySubBox.add(categorySubJLabel);
        categorySubBox.add(Box.createVerticalStrut(10));
        categorySubBox.add(categorySubJComboBox);
        boxTitle.add(categorySubBox);
        boxTitle.add(Box.createHorizontalStrut(10));

        statusSubBox = Box.createVerticalBox();
        statusSubBox.add(statusSubJLabel);
        statusSubBox.add(Box.createVerticalStrut(10));
        statusSubBox.add(statusSubJComboBox);
        boxTitle.add(statusSubBox);

        boxRight.add(boxTitle);
        //boxRight.add(Box.createVerticalStrut(74));
        boxRight.add(Box.createVerticalStrut(80));

        JScrollPane jScrollPane = new JScrollPane(
                table,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        Box tableBox = Box.createHorizontalBox();
        tableBox.add(jScrollPane);
        boxRight.add(tableBox);

        boxMain.add(boxRight);

        productPanel.add(boxMain);
    }

    private void setPanel() {
        productPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 30));
    }

}
