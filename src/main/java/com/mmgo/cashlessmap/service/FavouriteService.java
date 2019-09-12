package com.mmgo.cashlessmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmgo.cashlessmap.entity.Favourite;
import com.mmgo.cashlessmap.entity.Favourites;
import com.mmgo.cashlessmap.repository.FavouriteRepository;

@Service
public class FavouriteService {
	@Autowired
	private FavouriteRepository favouritereposity;
	
	
	public Favourites findByUsername(String username){
		 Favourites favourites = new Favourites();
		List<String> result = favouritereposity.findByUsername(username);
		for(String line : result) {
			String[] elements = line.split(",",0);
			Favourite favourite = new Favourite();
			favourite.setUsername(elements[0]);
			favourite.setStoreId(elements[1]);
			favourites.add(favourite);
		
		}
		return favourites;
	}
	
	public Boolean findByStoreId(String storeid) {
		String result = favouritereposity.findByStoreId(storeid);
		if(storeid.equals(result)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void save(Favourite favourite) {
		favouritereposity.save(favourite);
	}
	
	public void remove(String storeId) {
		favouritereposity.removeByStoreId(storeId);
	}
	
}
