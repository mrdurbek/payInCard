package com.payInCard.payInCardApp.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card {
	private Integer id;
	private String username;
	private Integer number;
	private double balance;
	private Date expired_date;
	private boolean active;
	private List<Income> incomeList = new ArrayList<Income>();
	private List<Outcome> outcomeList = new ArrayList<Outcome>();
	
	public List<Income> getIncomeList() {
		return incomeList;
	}

	public List<Outcome> getOutcomeList() {
		return outcomeList;
	}
	
	public void addOutcome(Outcome outcome) {
		outcomeList.add(outcome);
	}
	
	public void addIncome(Income income) {
		incomeList.add(income);
	}

	public Card() {}
	
	public Card(Integer id , String username, Integer number, Integer balance, Date expired_date, boolean active) {
		this.id = id;
		this.username = username;
		this.number = number;
		this.balance = balance;
		this.expired_date = expired_date;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
