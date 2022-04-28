package com.payInCard.payInCardApp.payload;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private List<Card> cardList = new ArrayList<Card>();
	
	public List<Card> getCardList() {
		return cardList;
	}
	public void addCard(Card card) {
		cardList.add(card);
	}

	public User() {}
	
	public User(String firstname, String lastname, String username , String password , List<Card> cardList) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.cardList = cardList;
	}
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
