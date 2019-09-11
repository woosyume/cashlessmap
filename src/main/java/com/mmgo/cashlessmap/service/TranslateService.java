package com.mmgo.cashlessmap.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.Translate;
import com.mmgo.cashlessmap.repository.TranslateRepository;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranslateService {

    @Autowired
    private TranslateRepository todoRepository;
    
    private String credential =  "AIzaSyAEuceCXpy1UCZs9J6ic-XHtSafbntDFeA\n";
    
    private Gson gson = new Gson();

    public List<Translate> findTodos() {
        return todoRepository.findAll();
    }

    public Translate save(Translate todo) {
        return todoRepository.save(todo);
    }
    
    public String translate(Translate todo) throws JsonSyntaxException, ParseException, IOException, HttpException {
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(createQueryHttpPost(todo));) {
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
			builder = new URIBuilder("https://translation.googleapis.com/language/translate/v2");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	builder.setParameter("q", todo.getText()).setParameter("target", todo.getTargetLanguage()).setParameter("key",credential);
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
