package com.project.SpringCafeUI.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.project.SpringCafeUI.controller.EmployeeController;
import com.project.SpringCafeUI.entity.Employee;
import com.project.SpringCafeUI.repository.EmployeeRepository;
import com.project.SpringCafeUI.utils.FontSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EmployeePage {
    private JLabel idJLabel, nameJLabel,
            ageJLabel, sexJLabel,
            titleJLabel, salaryJLabel,
            startDateJLabel, statusJLabel,
            searchJLabel;
    private JTextField idJTextField, nameJTextField, ageJTextField,
            salaryJTextField, startDateJTextField,
            searchJTextField;
    private JButton addJButton, updateJButton, deleteJButton;
    private JComboBox<String> sexJComboBox, titleJComboBox, statusJComboBox;
    private DefaultComboBoxModel<String> sexDfComboBoxModel, titleDfComboBoxModel, statusDfComboBoxModel;
    private JTable table;
    private DefaultTableModel dfTableModel;
    private final EmployeeController employeeController;
    private final Font fontB16 = FontSize.fontBold16();
    private final Font font16 = FontSize.fontPlain16();
    private final Color color = Color.WHITE;
    private final JPanel employeePanel;
    private final EmployeeRepository repository;

    @Autowired
    public EmployeePage(@Lazy EmployeeController employeeController, EmployeeRepository repository) {
        this.employeeController = employeeController;
        this.repository = repository;
        employeePanel = new JPanel();
        this.setJPanel();
        this.setComponents();
        this.setLocations();

    }

    private void setLocations() {
        this.setNorth();
        this.setCenter();
        this.setSouth();
    }

    private void setComponents() {
        this.setJLabel();
        this.setJTextField();
        this.setJButton();
        this.setJComboBox();
        this.setJTable();
    }

    private void setNorth() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 30, 20));
        //Dòng 1
        panel.add(idJLabel);
        panel.add(idJTextField);
        panel.add(nameJLabel);
        panel.add(nameJTextField);
        //End dòng 1
        //Dòng 2
        panel.add(ageJLabel);
        panel.add(ageJTextField);
        panel.add(sexJLabel);
        panel.add(sexJComboBox);
        //End dòng 2

        //Dòng 3
        panel.add(titleJLabel);
        panel.add(titleJComboBox);
        panel.add(salaryJLabel);
        panel.add(salaryJTextField);
        //End dòng 3

        //Dòng 4
        panel.add(startDateJLabel);
        panel.add(startDateJTextField);
        panel.add(statusJLabel);
        panel.add(statusJComboBox);
        //End dòng 4
        Box boxMain = Box.createHorizontalBox();

        boxMain.add(Box.createHorizontalStrut(60));
        boxMain.add(panel);
        boxMain.add(Box.createHorizontalStrut(60));
        employeePanel.add(boxMain, BorderLayout.NORTH);
    }

    private void setCenter() {
        Box boxMain = Box.createVerticalBox();
        boxMain.add(Box.createVerticalStrut(20));
        Box boxTable = Box.createHorizontalBox();

        boxTable.add(Box.createHorizontalStrut(60));

        JScrollPane jScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        boxTable.add(jScrollPane);

        boxTable.add(Box.createHorizontalStrut(60));
        boxMain.add(boxTable);
        boxMain.add(Box.createVerticalStrut(20));

        employeePanel.add(boxMain, BorderLayout.CENTER);
    }

    private void setSouth() {
        Box boxMain = Box.createVerticalBox();

        boxMain.add(Box.createVerticalStrut(20));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panel.add(searchJLabel);
        panel.add(searchJTextField);
        panel.add(addJButton);
        panel.add(updateJButton);
        panel.add(deleteJButton); //Xóa trắng
        boxMain.add(panel);
        boxMain.add(Box.createVerticalStrut(20));
        employeePanel.add(boxMain, BorderLayout.SOUTH);
    }

    private void setJLabel() {
        idJLabel = new JLabel("Mã nhân viên:");
        idJLabel.setFont(fontB16);

        nameJLabel = new JLabel("Tên nhân viên:");
        nameJLabel.setFont(fontB16);

        ageJLabel = new JLabel("Tuổi:");
        ageJLabel.setFont(fontB16);

        sexJLabel = new JLabel("Giới tính:");
        sexJLabel.setFont(fontB16);

        titleJLabel = new JLabel("Chức vụ:");
        titleJLabel.setFont(fontB16);

        salaryJLabel = new JLabel("Lương:");
        salaryJLabel.setFont(fontB16);

        startDateJLabel = new JLabel("Ngày vào làm:");
        startDateJLabel.setFont(fontB16);

        statusJLabel = new JLabel("Trạng thái làm việc");
        statusJLabel.setFont(fontB16);

        searchJLabel = new JLabel("Nhập tên sản phẩm cần tìm:");
        searchJLabel.setFont(fontB16);
    }

    private void setJTextField() {
        idJTextField = new JTextField(10);
        idJTextField.setEditable(false);
        idJTextField.setFocusable(false);
        idJTextField.setFont(font16);

        nameJTextField = new JTextField(10);
        nameJTextField.setFont(font16);

        ageJTextField = new JTextField(10);
        ageJTextField.setFont(font16);

        salaryJTextField = new JTextField(10);
        salaryJTextField.setFont(font16);


        startDateJTextField = new JTextField(10);
        startDateJTextField.setFont(font16);
        startDateJTextField.setEditable(false);
        startDateJTextField.setFocusable(false);

        searchJTextField = new JTextField(15);
        searchJTextField.setFont(FontSize.fontPlain24());
        searchJTextField.setForeground(Color.RED);
        searchJTextField.getDocument().addDocumentListener(employeeController);
    }

    private void setJButton() {
        addJButton = new JButton("Thêm");
        addJButton.setFont(fontB16);
        addJButton.setFocusPainted(false);
        addJButton.setBackground(color);
        addJButton.addActionListener(employeeController);

        updateJButton = new JButton("Cập nhật");
        updateJButton.setFont(fontB16);
        updateJButton.setFocusable(false);
        updateJButton.setBackground(color);
        updateJButton.addActionListener(employeeController);

        deleteJButton = new JButton("Xóa trắng");
        deleteJButton.setFont(fontB16);
        deleteJButton.setFocusPainted(false);
        deleteJButton.setBackground(color);
        deleteJButton.addActionListener(employeeController);
    }

    private void setJComboBox() {
        sexDfComboBoxModel = new DefaultComboBoxModel<String>();
        sexJComboBox = new JComboBox<String>(sexDfComboBoxModel);
        sexJComboBox.setFont(font16);
        sexJComboBox.setBackground(color);
        sexJComboBox.addItem("Nam");
        sexJComboBox.addItem("Nữ");

        titleDfComboBoxModel = new DefaultComboBoxModel<String>();
        titleJComboBox = new JComboBox<String>(titleDfComboBoxModel);
        titleJComboBox.setFont(font16);
        titleJComboBox.setBackground(color);
        titleJComboBox.addItem("Quản lý");
        titleJComboBox.addItem("Thu ngân");
        titleJComboBox.addItem("Pha chế");

        statusDfComboBoxModel = new DefaultComboBoxModel<String>();
        statusJComboBox = new JComboBox<String>(statusDfComboBoxModel);
        statusJComboBox.setFont(font16);
        statusJComboBox.setBackground(color);
        statusJComboBox.addItem("Đang làm việc");
        statusJComboBox.addItem("Ngưng làm việc");
    }

    private void setJTable() {
        String[] headers = "Mã NV;Họ tên nhân viên;Tuổi;Giới tính;Chức vụ;Lương;Ngày vào làm;Trạng thái".split(";");
        dfTableModel = new DefaultTableModel(headers, 0);
        table = new JTable(dfTableModel);
        table.setAutoCreateRowSorter(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFont(FontSize.fontPlain16());
        table.addMouseListener(employeeController);
        this.loadTable();//first load
    }

    //Load one row table
    private void loadOneRow(Employee employee) {
        DecimalFormat formater = new DecimalFormat("#.0");
        this.dfTableModel.addRow(new Object[] {
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.isSex() ? "Nữ" : "Nam",
                employee.getRole(),
                formater.format(employee.getSalary()),
                employee.getStartDate(),
                employee.isStatus() ? "Đang làm việc" : "Ngưng làm việc"
        });
    }

    public void loadTable(){
        this.getDfTableModel().setRowCount(0);
        repository.findAll().forEach(this::loadOneRow);
    }

    public void loadTable(String name){
        this.getDfTableModel().setRowCount(0);
        repository.findByNameContaining(name).forEach(this::loadOneRow);
    }

    private void setJPanel() {
        employeePanel.setLayout(new BorderLayout());
    }
}
