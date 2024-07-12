package com.project.SpringCafeUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.project.SpringCafeUI.entity.Card;
import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.view.CardNumberPage;
import com.project.SpringCafeUI.view.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CardNumberController implements ActionListener {

	private final CardNumberPage cardNumberPage;

	private final HomePage homePage;
	
	@Autowired
	public CardNumberController(@Lazy CardNumberPage cardNumberPage,@Lazy HomePage homePage) {
		this.cardNumberPage = cardNumberPage;
		this.homePage = homePage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		homePage.getCardNumberValueJLabel().setText(command);
		cardNumberPage.getFrame().dispose();
	}
	
	public List<Card> getCards() {
		return List.of(new Card(1, 10, false),
				       new Card(2, 11, true),
				       new Card(3, 12, true));
	}
}
