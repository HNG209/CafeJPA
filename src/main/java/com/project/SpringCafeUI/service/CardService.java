package com.project.SpringCafeUI.service;

import com.project.SpringCafeUI.entity.Card;
import com.project.SpringCafeUI.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAll(){
        return cardRepository.findAll();
    }
}
