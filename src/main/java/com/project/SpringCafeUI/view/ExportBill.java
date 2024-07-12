package com.project.SpringCafeUI.view;



import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.entity.OrderDetail;
import com.project.SpringCafeUI.utils.FileSaver;
import com.project.SpringCafeUI.view.HomePage;

public class ExportBill extends JFrame {
    private JTextArea odArea;
    public ExportBill(Order order) {
//        List<OrderDetail> orderDetails = new OrderDetailDAO().getListOrderDetail("idOrder", order.getId());
//
//        odArea = new JTextArea(8, 40);
//        JScrollPane as = new JScrollPane(odArea);
//        odArea.setEditable(false);
//
//        odArea.append("\n\n   CAFE JAVA\n\n");
//        odArea.append("   \tHoá đơn bán hàng\n");
//        odArea.append("   \t---------------------------\n");
//
//
//        odArea.append("   \tMã hoá đơn:\t\t" + order.getId() + "\n");
//        odArea.append("   \tNgày lập:\t\t" + order.getDate() + "\n");
//        System.out.println(order.getDate());
//        odArea.append("   \tNgười lập:\t\t" + order.getEmployee().getName() + "\n");
//        odArea.append("   \t---------------------------\n\n");
//
//
//        odArea.append("\n   Chi tiết đặt\n");
//        odArea.append("-------------------------------------------------------------------------------------------------------------------------");
//
//        odArea.append("\n   STT\tTên món\t\tĐơn giá\tSố lượng\tThành tiền\n");
//        odArea.append("-------------------------------------------------------------------------------------------------------------------------\n");
//
//        //detail here
//
//        DecimalFormat decimalFormat1 = new DecimalFormat("#,###");
//
//        for(int i = 0;i < orderDetails.size();i++) {
//            String name = orderDetails.get(i).getDrink().getName();
//            String unitPrice = String.valueOf(orderDetails.get(i).getUnitPrice());
//            String quantity = String.valueOf(orderDetails.get(i).getQuantity());
//            double total = Double.parseDouble(quantity) * Double.parseDouble(unitPrice);
//            addFormatItem((i + 1) + "", name, unitPrice, quantity, decimalFormat1.format(total) + "");
//        }
//
//        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
//        odArea.append("\n-------------------------------------------------------------------------------------------------------------------------\n");
//        addFormatItem("", "Thành tiền", "", "", decimalFormat.format(orderDetails.get(0).getOrder().getTotalDue())+ "");
//
//        odArea.append("-------------------------------------------------------------------------------------------------------------------------\n");
//        HomePage homePage = HomePage.getInstance();
//
//
//
//        addFormatItem("", "Tổng tiền:","", "", decimalFormat.format(orderDetails.get(0).getOrder().getTotalDue()) + "");
//        addFormatItem("", "Tiền nhận:","", "", decimalFormat.format(Double.parseDouble(homePage.getPayByEmployeeJTextField().getText())) + "");
//        addFormatItem("", "Tiền thừa:","", "", decimalFormat.format(Double.parseDouble(homePage.getPayByEmployeeChangeValueJLabel().getText())) + "");
//
//        odArea.append("\n\n\t\tCAFE JAVA xin cảm ơn quý khách");
//
//        FileSaver.saveToFile(odArea);
//
//        this.add(as, BorderLayout.CENTER);
//        this.setTitle("Hóa đơn");
//        this.setSize(500, 700);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        this.setResizable(false);
//        this.setVisible(true);
    }

    public String addFormatItem(String stt, String name, String price, String sl, String total) {
        String[] words = name.split(" ");
        String result = "";
        if(words.length <= 3) {
            result = "   " + stt + "\t" + name + "\t\t" + price + "\t" + sl + "\t" + total + "\n";
            odArea.append(result);
        }
        else {
            result = "   " + stt + "\t" + words[0] + " " + words[1] + " " + words[2] + "\t\t" + price + "\t" + sl + "\t" + total + "\n";
            odArea.append(result);
            String remaining = "";
            for(int i = 3; i < words.length; i++) {
                remaining += words[i] + " ";
            }
            addFormatItem("", remaining, "", "", "");
        }
        return result;
    }
}

