package com.mmgo.cashlessmap.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.Store;
import com.mmgo.cashlessmap.entity.Stores;
import com.mmgo.cashlessmap.entity.Translate;

import com.mmgo.cashlessmap.utility.Option;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranslateService {

    private final String CREDENTIAL =  "AIzaSyAEuceCXpy1UCZs9J6ic-XHtSafbntDFeA";
    private final String TRANSLATE_HOST = "https://translation.googleapis.com/language/translate/v2";
    
    private Gson gson = new Gson();

    public Stores translate(Stores stores, Option option) {
      try {
        for(Store store : stores.getStores()) {
          Translate translate = new Translate(store.name, option.lang);
          store.translatedName = translate(translate);
          translate.setText(store.prLong);
          store.translatedPrLong = translate(translate);
          translate.setText(store.prShort);
          store.translatedPrShort = translate(translate);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (HttpException e) {
        e.printStackTrace();
      }
      return stores;
    }

    public String translate(Translate translate) throws JsonSyntaxException, ParseException, IOException, HttpException {
        if(translate.getTargetLanguage().equals("ja")) {
          return translate.getText();
        }
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(createQueryHttpPost(translate));) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                return parseTranslationText(response.getEntity());
            } else {
                throw new HttpException(Integer.toString(statusCode));
            }
        }
    }

    private HttpPost createQueryHttpPost(Translate todo) {
        
    	URIBuilder builder = null;
      try {
        builder = new URIBuilder(TRANSLATE_HOST);
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
      builder.setParameter("q", todo.getText())
          .setParameter("target", todo.getTargetLanguage())
          .setParameter("key", CREDENTIAL);
      try {
        return new HttpPost(builder.build());
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    	return null;
    }
    
    private String parseTranslationText(HttpEntity entity) throws JsonSyntaxException, ParseException, IOException {
        JsonObject object = gson.fromJson(EntityUtils.toString(entity, "UTF-8"), JsonObject.class);
        
        return object
            .getAsJsonObject("data")
            .getAsJsonArray("translations")
            .get(0)
            .getAsJsonObject()
            .get("translatedText")
            .getAsString();
    }
}
