package com.payInCard.payInCardApp.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.payInCard.payInCardApp.payload.Card;

@Service
public class CardRepository {
	
	private List<Card> cardList = new ArrayList<Card>();
	
	public List<Card> getCardList() {
		return cardList;
	}
	
	public void addCard(Card card) {
		cardList.add(card);
	}
	
	public Card getCardByNumber(Integer number) {
		Iterator<Card> iterator = this.cardList.iterator();
		while(iterator.hasNext()) {
			Card card = iterator.next();
			if(card.getNumber().equals(number)) {
				return card;
			}
		}
		return null;
	}
}
