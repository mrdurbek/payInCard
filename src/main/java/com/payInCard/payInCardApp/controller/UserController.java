package com.payInCard.payInCardApp.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payInCard.payInCardApp.Service.MyAuthService;
import com.payInCard.payInCardApp.payload.Card;
import com.payInCard.payInCardApp.payload.Income;
import com.payInCard.payInCardApp.payload.Outcome;
import com.payInCard.payInCardApp.payload.TransferDto;
import com.payInCard.payInCardApp.payload.User;
import com.payInCard.payInCardApp.repository.CardRepository;
import com.payInCard.payInCardApp.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	CardRepository cardRepository;
	@Autowired
	MyAuthService myAuthService;
	
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody User user){
		Iterator<User> iterator = userRepository.getUserList().iterator();
		while(iterator.hasNext()) {
			User testUser = iterator.next();			
			if(user.getUsername().equals(testUser.getUsername())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("This user already existed");
			}
		}
		userRepository.addUser(user);
		myAuthService.addUser(user.getUsername(), user.getPassword());
		return ResponseEntity.ok("OK");
	}
	
	@GetMapping
	public List<User> getUser(){
		return userRepository.getUserList();
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		User user = userRepository.getUserByUserName(username);
		if(!user.equals(null)) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(404).body("Not Found");
	}
	
	@PostMapping("/{username}/transfer")
	public ResponseEntity<?> transferInCard(@PathVariable String username , @RequestBody TransferDto transferDto){
		User user = userRepository.getUserByUserName(username);
		if(!user.equals(null)) {
			Iterator<Card> iterator = user.getCardList().iterator();	
			while(iterator.hasNext()) {
				Card card = iterator.next();
				if(card.getId().equals(transferDto.getCardId())) {
					double currentBalance = card.getBalance();
					if(currentBalance-(transferDto.getBalance()+transferDto.getBalance()*0.1)<0) {
						return ResponseEntity.status(405).body("There isn't enough money in this card");
					}

					Card toCard = cardRepository.getCardByNumber(transferDto.getCardNumber());
					card.setBalance(currentBalance-(transferDto.getBalance()+transferDto.getBalance()*0.01));
					Outcome outcome = new Outcome(1 , card.getId() , toCard.getId() , new Date() , transferDto.getBalance()*0.01);
					card.addOutcome(outcome);
					
					toCard.setBalance(toCard.getBalance()+transferDto.getBalance());
					Income income = new Income(1 , card.getId() , toCard.getId() , new Date());
					toCard.addIncome(income);
					return ResponseEntity.ok("OK");
				}
			}
		}
		return ResponseEntity.status(404).body("User Not Found");
	}
}
