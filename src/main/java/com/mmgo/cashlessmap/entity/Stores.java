package com.mmgo.cashlessmap.entity;

import java.util.ArrayList;
import java.util.List;

import com.mmgo.cashlessmap.utility.Option;

public class Stores {
	private List<Store> stores = new ArrayList<Store>();
	
	public void add(Store store) {
		this.stores.add(store);
	}
	
	public List<Store> getStores() {
		return this.stores;
	}

	public Stores filterJsonValue(Option option){
		Stores newStores = new Stores();
		for(Store store: this.getStores()) {
			String[] pays = store.eMoney.split(",",0);
			String[] creditCards = store.creditCard.split(",", 0);
			boolean find = false;
			for(String pay : pays) {
				if(option.pay.contains(pay)) {
					find = true;
					break;
				}
			}
			for(String creditCard : creditCards) {
				if(option.creditCards.contains(creditCard)) {
					find = true;
				}
			}
			if(find) {
				newStores.add(store);
			}
		}
		return newStores;
	}
}
