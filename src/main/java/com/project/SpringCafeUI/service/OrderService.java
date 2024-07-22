package com.project.SpringCafeUI.service;

import com.project.SpringCafeUI.entity.*;
import com.project.SpringCafeUI.repository.*;
import com.project.SpringCafeUI.view.CardNumberPage;
import com.project.SpringCafeUI.view.Dashboard;
import com.project.SpringCafeUI.view.HomePage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HomePage homePage;

    @Transactional
    public void cancelOrder(int id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("couldn't found order to cancel"));
        Card card = order.getCard();
        card.setStatus(true);//make the tag available again
        cardRepository.save(card);

        orderRepository.delete(order);
    }

    @Transactional
    public void done(int id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("couldn't found order to cancel"));

        order.setStatus(true);//set order status to true(done)

        Card card = order.getCard();
        card.setStatus(true);
        cardRepository.save(card);//make the tag available again

        orderRepository.save(order);
    }

    @Transactional
    public void saveOrder(){//save an order, input data from homepage
        int rows = homePage.getDfBillDefaultTableModel().getRowCount();

        Date date = Date.valueOf(LocalDate.now());
        double total = Double.parseDouble(homePage.getTotalValueJLabel().getText().trim());
        boolean status = false;
        Card card = cardRepository.findByNumber(Integer.parseInt(homePage.getCardNumberValueJLabel().getText())).get(0);
        Employee employee = accountService.getAccount().getEmployee();

        Order order = new Order();

        order.setDate(date);
        order.setDescription("");
        order.setStatus(status);
        order.setTotalDue(total);
        order.setCard(card);
        order.setEmployee(employee);

        Set<OrderDetail> orderDetails = new HashSet<>();
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

            orderDetails.add(detail);
        }

        card.setStatus(false);
        cardRepository.save(card);

        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
    }
}
