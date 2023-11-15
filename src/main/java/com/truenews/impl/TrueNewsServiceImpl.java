package com.truenews.impl;

import com.truenews.config.AppConfig;
import com.truenews.dto.SearchResponse;
import com.truenews.rest.TrueNewsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TrueNewsServiceImpl {

    Log LOGGER= LogFactory.getLog(TrueNewsServiceImpl.class);
    private String GNEWS_BASE_URL="https://gnews.io/api/v4/search?q=";

    @Autowired
    AppConfig appConfig;

    public SearchResponse getSearchResponse(String searchString, String numberOfArticles) {
        String url= GNEWS_BASE_URL + searchString +"&max="+ numberOfArticles +"&lang=en&apikey=" + appConfig.getApikey() ;
        LOGGER.info("URL for searchByKeyWord:: " + url);
        RestTemplate restTemplate = new RestTemplate();
        SearchResponse listOfArticles= null;
        try {
            listOfArticles = restTemplate.getForObject(url, SearchResponse.class);
        }catch (Exception e){
            LOGGER.error("error while calling GNews " + e.getMessage());
        }
        return listOfArticles;
    }

    public SearchResponse getSearchResponseByTitle(String title) {
        String url= GNEWS_BASE_URL+ title +"&in=title"  +"&lang=en&apikey=" + appConfig.getApikey();
        LOGGER.info("URL for searchByKeyWord:: " + url);
        RestTemplate restTemplate = new RestTemplate();
        SearchResponse listOfArticles= null;
        try {
            listOfArticles = restTemplate.getForObject(url, SearchResponse.class);
        }catch (Exception e){
            LOGGER.error("error while calling GNews " + e.getMessage());
        }
        return listOfArticles;
    }
}
