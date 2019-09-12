package com.mmgo.cashlessmap;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.GurunaviApiClient;
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
