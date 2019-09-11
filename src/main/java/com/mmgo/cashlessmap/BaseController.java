package com.mmgo.cashlessmap;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.GurunaviApiClient;
import com.mmgo.cashlessmap.entity.Store;
import com.mmgo.cashlessmap.entity.Stores;
import com.mmgo.cashlessmap.entity.Translate;
import com.mmgo.cashlessmap.service.TranslateService;
import com.mmgo.cashlessmap.utility.Option;
import com.mmgo.cashlessmap.utility.RequestParser;

import org.apache.http.HttpException;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @GetMapping("/")
    public String home(Model model) {
        return "index";
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
