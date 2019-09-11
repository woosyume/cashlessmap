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
	
	public void remote(Store store) {
		this.stores.remove(store);
	}
	
	public Stores filterJsonValue(Option option){
		Stores newStores = new Stores();
		for(Store store: this.getStores()) {
			String[] pays = store.eMoney.split(",",0);	
			for(String pay : pays) {
				if(option.pay.contains(pay)) {
					newStores.add(store);
					break;
				}
			}
		}
		return newStores;
	}
}
