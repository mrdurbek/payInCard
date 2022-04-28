package com.payInCard.payInCardApp.payload;

import java.util.Date;

public class Income {
	private Integer id;
	private Integer from_card_id;
	private Integer to_card_id;
	private Date date;
	
	public Income(Integer id, Integer from_card_id, Integer to_card_id, Date date) {
		super();
		this.id = id;
		this.from_card_id = from_card_id;
		this.to_card_id = to_card_id;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFrom_card_id() {
		return from_card_id;
	}
	public void setFrom_card_id(Integer from_card_id) {
		this.from_card_id = from_card_id;
	}
	public Integer getTo_card_id() {
		return to_card_id;
	}
	public void setTo_card_id(Integer to_card_id) {
		this.to_card_id = to_card_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
		
}
