package com.mmgo.cashlessmap.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.Store;
import com.mmgo.cashlessmap.entity.Stores;

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

    public Stores translate(Stores stores, String targetLang) throws JsonSyntaxException, ParseException, IOException, HttpException {
        for(Store store : stores.getStores()) {
            try (CloseableHttpResponse response = HttpClients.createDefault().execute(createQueryHttpPost(store.prShort, targetLang));) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    store.prShort = parseTranslationText(response.getEntity());
                } else {
                    throw new HttpException(Integer.toString(statusCode));
                }
            }
        }
        return stores;
    }

    private HttpPost createQueryHttpPost(String text, String targetLang) {
        System.out.println(text);
        System.out.println(targetLang);

        URIBuilder builder = null;
        try {
            builder = new URIBuilder(TRANSLATE_HOST);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        builder.setParameter("q", text)
            .setParameter("target", targetLang)
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
