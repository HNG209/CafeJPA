package com.project.SpringCafeUI.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import com.project.SpringCafeUI.controller.DashboardController;
import com.project.SpringCafeUI.utils.BackgroundColor;
import com.project.SpringCafeUI.utils.BorderRadius;
import com.project.SpringCafeUI.utils.DateTime;
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
public class Dashboard {
    @Setter
    @Getter
    private JButton dashboardJButton,
            shellJButton,
            billJButton,
            productJButton,
            statisticalJButton,
            employeeJButton;
    private JButton logOutJButton;
    private JLabel nameJLabel, positionJLabel, employeeIDJLabel;
    private JLabel timeJLabel, dateJLabel;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private final Color fontColor = Color.white;
    private final Color backgroundColor = BackgroundColor.orangeMain();
    private final DashboardController dashboardController;

    private JFrame frame;

    private final HomePage homePage;
    private final SellPage sellPage;
    private final BillPage billPage;
    private final ProductPage productPage;
    private final EmployeePage employeePage;

    @Autowired
    public Dashboard(@Lazy DashboardController controller, HomePage homePage, SellPage sellPage, BillPage billPage, ProductPage productPage, EmployeePage employeePage) {
        this.dashboardController = controller;
        this.homePage = homePage;
        this.sellPage = sellPage;
        this.billPage = billPage;
        this.productPage = productPage;
        this.employeePage = employeePage;
        show();
    }

    @PostConstruct
    public void show(){
        if (!GraphicsEnvironment.isHeadless()) {
            this.frame = new JFrame("Dashboard");
            this.setComponents();
            this.setLocations();
            this.setJFrame();
            this.addActionListener();
        }
    }

    private void setLocations() {
        this.setNorth();
        this.setWest();
        this.setCenter();
    }

    private void setComponents() {
        this.setJButton();
        this.setLabel();
    }

    private void addActionListener() {
        this.addActionListenerJButton();
    }

    public void setJButton() {
        //Left button
        dashboardJButton = new JButton("Trang chủ", new ImageIcon("icons\\about_25px.png"));
        dashboardJButton.setFont(FontSize.fontBold24());
        dashboardJButton.setForeground(fontColor);
        dashboardJButton.setBackground(backgroundColor);
        dashboardJButton.setFocusPainted(false);

        shellJButton = new JButton("Đơn đặt", new ImageIcon("icons\\shopping_cart_25px.png"));
        shellJButton.setFont(FontSize.fontBold24());
        shellJButton.setForeground(fontColor);
        shellJButton.setBackground(backgroundColor);
        shellJButton.setFocusPainted(false);

        billJButton = new JButton("Hóa đơn", new ImageIcon("icons\\purchase_order_25px.png"));
        billJButton.setFont(FontSize.fontBold24());
        billJButton.setForeground(fontColor);
        billJButton.setBackground(backgroundColor);
        billJButton.setFocusPainted(false);

        productJButton = new JButton("Thức uống", new ImageIcon("icons\\cardboard_box_25px.png"));
        productJButton.setFont(FontSize.fontBold24());
        productJButton.setForeground(fontColor);
        productJButton.setBackground(backgroundColor);
        productJButton.setFocusPainted(false);

        statisticalJButton = new JButton("Thống kê", new ImageIcon("icons\\increase_25px.png"));
        statisticalJButton.setFont(FontSize.fontBold24());
        statisticalJButton.setForeground(fontColor);
        statisticalJButton.setBackground(backgroundColor);
        statisticalJButton.setFocusPainted(false);

        employeeJButton = new JButton("Nhân viên", new ImageIcon("icons\\user_groups_25px.png"));
        employeeJButton.setFont(FontSize.fontBold24());
        employeeJButton.setForeground(fontColor);
        employeeJButton.setBackground(backgroundColor);
        employeeJButton.setFocusPainted(false);
        //End left button

        //Title button north
        Color whiteBackground = Color.white;
        Border borderRadius = new BorderRadius.RoundedBorder(8, Color.WHITE);

        logOutJButton = new JButton("Đăng xuất");
        logOutJButton.setFont(FontSize.fontPlain16());
        logOutJButton.setFocusPainted(false);
        logOutJButton.setBorder(borderRadius);
        logOutJButton.setBackground(whiteBackground);
        logOutJButton.addActionListener(dashboardController);

        //End title button north
    }

    private void addActionListenerJButton() {
        dashboardJButton.addActionListener(dashboardController);
        shellJButton.addActionListener(dashboardController);
        billJButton.addActionListener(dashboardController);
        productJButton.addActionListener(dashboardController);
        statisticalJButton.addActionListener(dashboardController);
        employeeJButton.addActionListener(dashboardController);
    }

    private void setLabel() {
        nameJLabel = new JLabel();
        nameJLabel.setFont(FontSize.fontBold16());
        nameJLabel.setForeground(fontColor);

        employeeIDJLabel = new JLabel();
        employeeIDJLabel.setFont(FontSize.fontBold16());
        employeeIDJLabel.setForeground(fontColor);

        positionJLabel = new JLabel();
        positionJLabel.setFont(FontSize.fontBold16());
        positionJLabel.setForeground(fontColor);

        timeJLabel = new JLabel();
        timeJLabel.setFont(FontSize.fontBold16());
        timeJLabel.setForeground(fontColor);

        dateJLabel = new JLabel();
        dateJLabel.setFont(FontSize.fontBold16());
        dateJLabel.setForeground(fontColor);
    }

    private void setNorth() {
        JPanel titleJPanel = new JPanel(new BorderLayout());
        //West panel
        JPanel contentTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        contentTitlePanel.add(logOutJButton);

        contentTitlePanel.add(nameJLabel);

        JLabel line = new JLabel("-");
        line.setFont(FontSize.fontBold16());
        line.setForeground(fontColor);

        contentTitlePanel.setBackground(backgroundColor);

        contentTitlePanel.add(line);
        contentTitlePanel.add(positionJLabel);

        titleJPanel.add(contentTitlePanel, BorderLayout.WEST);
        //End west panel

        //East panel
        JPanel contentTimeJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 16));
        contentTimeJPanel.setBackground(backgroundColor);
        contentTimeJPanel.add(timeJLabel);
        contentTimeJPanel.add(dateJLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();

        titleJPanel.add(contentTimeJPanel, BorderLayout.EAST);
        //End east panel

        titleJPanel.setBackground(backgroundColor);

        frame.add(titleJPanel, BorderLayout.NORTH);
    }

    private void setWest() {
        Font font = new Font("Arial", Font.BOLD, 24);

        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        JPanel iconJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel iconLabel = new JLabel(new ImageIcon("icons\\icon-java_80px.png"));

        iconJPanel.add(iconLabel);
        iconJPanel.setBackground(BackgroundColor.orangeMain());

        buttonPanel.add(iconJPanel);
        buttonPanel.add(dashboardJButton);
        buttonPanel.add(shellJButton);
        buttonPanel.add(billJButton);
        buttonPanel.add(productJButton);
        buttonPanel.add(employeeJButton);
        buttonPanel.add(statisticalJButton);

        frame.add(buttonPanel, BorderLayout.WEST);
    }

    private void setCenter() {
        JPanel centerJPanel = new JPanel();
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(homePage.getHomePanel(), "Trang chủ");
        cardPanel.add(sellPage.getSellPanel(), "Đơn đặt");
        cardPanel.add(billPage.getBillPanel(), "Hóa đơn");
        cardPanel.add(productPage.getProductPanel(), "Thức uống");
        cardPanel.add(employeePage.getEmployeePanel(), "Nhân viên");
        //cardPanel.add(statisticsJPanel, "Thống kê");

        centerJPanel.add(cardPanel, BorderLayout.CENTER);
        frame.add(centerJPanel, BorderLayout.CENTER);
    }

    private void setJFrame() {
        frame.setTitle("Coffee");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.addWindowListener(dashboardController);
        frame.setVisible(false);
    }

    private void updateTime() {
        timeJLabel.setText(DateTime.getTime());
        dateJLabel.setText(DateTime.getDate());
    }


}
