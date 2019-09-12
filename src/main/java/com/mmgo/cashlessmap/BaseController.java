package com.mmgo.cashlessmap;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Response;

import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.Favourite;
import com.mmgo.cashlessmap.entity.Favourites;
import com.mmgo.cashlessmap.entity.GurunaviApiClient;
import com.mmgo.cashlessmap.entity.Store;
import com.mmgo.cashlessmap.entity.Stores;
import com.mmgo.cashlessmap.entity.Translate;
import com.mmgo.cashlessmap.entity.TranslateLanguages;
import com.mmgo.cashlessmap.entity.User;
import com.mmgo.cashlessmap.exception.ForbiddenException;
import com.mmgo.cashlessmap.service.FavouriteService;
import com.mmgo.cashlessmap.service.TranslateService;
import com.mmgo.cashlessmap.service.UserService;
import com.mmgo.cashlessmap.utility.Option;
import com.mmgo.cashlessmap.utility.RequestParser;

import org.apache.http.HttpException;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * このクラスは、ウェブアプリケーションの挙動を制御するためのコントローラークラスです。。
 * コントローラーとして使えるように、コードを記入してください。
 */
@Controller
public class BaseController {

    private static final String[] ALL_LANGUAGES = {"ja", "en", "zh-CN", "zh-TW", "ko", "es", "ru", "fr", "de"};

    @Autowired
    private TranslateService translateService;
    
    private GurunaviApiClient guruNaviApiClient = new GurunaviApiClient();
    
    @Autowired
    private TranslateLanguages translateLanguages;
    
    @Autowired
    private FavouriteService favouriteservice;
    
    @Autowired
    private UserService userservice;

    @GetMapping("/")
    public String home(Model model) {
        return "register";
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/favourite")
    @ResponseBody
    public Favourite postFavourite(@RequestParam("username") String username,@RequestParam("storeid") String storeid){
    	Boolean userResult = userservice.find(username);
    	if(userResult) {
    		Boolean favouriteResult = favouriteservice.findByStoreId(storeid);
    		if(!favouriteResult) {
        		Favourite favourite = new Favourite();
        		favourite.setUsername(username);
        		favourite.setStoreId(storeid);
        		favouriteservice.save(favourite);
        		System.out.println("A favourite has been created");
        		return favourite;
    		}else {
    			throw new ForbiddenException();
    		}
    	}else {
    		throw new ForbiddenException();
    	}
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/favourite")
    @ResponseBody
    public Favourites getFavourite(@RequestParam("username") String username) {
    	return favouriteservice.findByUsername(username);
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/user")
    public String postUser(@RequestParam String username) {
    	System.out.print(username);
    	Boolean result = userservice.find(username);
    	UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    	URI location = builder.path("/user/" + username).build().toUri();
    	if(!result) {
        	User user = new User();
        	user.setUsername(username);
        	userservice.save(user);
    	}
    	return "redirect:" + location.toString();
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/user/{username}")
    public String getUser(@PathVariable String username) {
    	Boolean result = userservice.find(username);
    	if(result) {
    		return "index";
    	}else {
    		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    		URI location = builder.path("/").build().toUri();
    		return "redirect:" + location.toString();
    	}
    }
    
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value="/navi")
    @ResponseBody
    public Stores json(@RequestBody String text) {
    	try {
			Option option = RequestParser.parse(text);
			
			Stores stores = guruNaviApiClient.execute(option);
			stores = stores.filterJsonValue(option);
			for(Store store : stores.getStores()) {
				Translate translate = new Translate(store.name, option.lang);
				store.translatedName  = translateService.translate(translate);
				translate.setText(store.prLong);
				store.translatedPrLong = translateService.translate(translate);
				translate.setText(store.prShort);
				store.translatedPrShort = translateService.translate(translate);
			}
			
    		return stores;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
}
