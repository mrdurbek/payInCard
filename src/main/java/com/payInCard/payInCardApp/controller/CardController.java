package com.payInCard.payInCardApp.controller;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payInCard.payInCardApp.payload.Card;
import com.payInCard.payInCardApp.payload.User;
import com.payInCard.payInCardApp.repository.CardRepository;
import com.payInCard.payInCardApp.repository.UserRepository;

@RestController
@RequestMapping("/api/card")
public class CardController {

	@Autowired
	CardRepository cardRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping
	public ResponseEntity<List<Card>> getCards(){
		return ResponseEntity.ok(cardRepository.getCardList());
	}
	
	@PostMapping
	public ResponseEntity<?> addCard(@RequestBody Card card){
		Iterator<User> iterator = userRepository.getUserList().iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getUsername().equals(card.getUsername())) {
				cardRepository.addCard(card);
				user.addCard(card);
				return ResponseEntity.ok("OK");
			}
		}
		return ResponseEntity.ok("Error occured");
	}
}
