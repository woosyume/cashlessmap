package com.mmgo.cashlessmap.entity;

import java.util.ArrayList;
import java.util.List;

public class Favourites {
	private List<Favourite> favourites = new ArrayList<>();
	
	public void add(Favourite favourite) {
		this.favourites.add(favourite);
	}
	
	public List<Favourite> getFavourites(){
		return this.favourites;
	}

}
