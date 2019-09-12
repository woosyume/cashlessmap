package com.mmgo.cashlessmap;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.Coordinate;
import com.mmgo.cashlessmap.entity.GoogleMapApiResponse;
import com.mmgo.cashlessmap.entity.GurunaviApiClient;
import com.mmgo.cashlessmap.entity.Stores;
import com.mmgo.cashlessmap.entity.Translate;
import com.mmgo.cashlessmap.service.GoogleMapApiService;
import com.mmgo.cashlessmap.service.TranslateService;
import com.mmgo.cashlessmap.utility.CoordinateParser;
import com.mmgo.cashlessmap.utility.Option;
import com.mmgo.cashlessmap.utility.RequestParser;

import org.apache.http.HttpException;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            Translate translate = new Translate(option.seachText, "ja");
            option.translatedSeachText=translateService.translate(translate);
            Stores stores = guruNaviApiClient.execute(option);
            stores = stores.filterJsonValue(option);

            return stores;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value="/international")
    @ResponseBody
    public Stores international(@RequestBody String text) {
        Option option = RequestParser.parse(text);

        JsonObject jsonObj = new Gson().fromJson(text, JsonObject.class);
        String lang = jsonObj.get("lang").getAsString();
        String storeId = jsonObj.get("storeId").getAsString();
        Stores stores = null;
        try {
            stores = guruNaviApiClient.execute(option);
        } catch (JsonSyntaxException | ParseException | IOException e) {
            e.printStackTrace();
        }
        try {
            stores = translateService.translate(stores, option);
        } catch (JsonSyntaxException | ParseException e) {
            e.printStackTrace();
        }
        return stores;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value="/map/duration")
	@ResponseBody
	public GoogleMapApiResponse duration(@RequestBody String request) {
		List<Coordinate> coordinates = CoordinateParser.parse(request);
		GoogleMapApiResponse googleMapApiResponse = GoogleMapApiService.getDirection(coordinates);
		return googleMapApiResponse;
    }
}