package com.mmgo.cashlessmap.entity;

import java.util.ArrayList;
import java.util.List;

public class Stores {
	private List<Store> stores = new ArrayList<Store>();
	
	public void add(Store store) {
		this.stores.add(store);
	}
	
	public List<Store> getStores() {
		return this.stores;
	}

}
