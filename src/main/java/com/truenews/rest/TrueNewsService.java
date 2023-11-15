package com.truenews.rest;

import com.truenews.config.AppConfig;
import com.truenews.dto.Article;
import com.truenews.dto.SearchResponse;
import com.truenews.exceptions.MissingParameterException;
import com.truenews.impl.TrueNewsServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.logging.*;

@RestController
// @Service("/truenews")
public class TrueNewsService    {

    Log LOGGER= LogFactory.getLog(TrueNewsService.class);


    @Autowired
    AppConfig appConfig;

    @Autowired
    TrueNewsServiceImpl trueNewsServiceImpl;
    private static final String template = "Hello, %s!";
    @GetMapping("/articles")
    public Article[] getArticles(@RequestParam(value = "name", defaultValue = "World") String name) {
        String API_KEY = appConfig.getApikey();

        //todo take this to property file
        String url= "https://gnews.io/api/v4/search?q=example&apikey="+API_KEY ;
        RestTemplate restTemplate = new RestTemplate();
        SearchResponse listOfArticles= null;
        try {
             listOfArticles = restTemplate.getForObject(url, SearchResponse.class);
        }catch (Exception e){
            LOGGER.error("error while calling GNews" + e.getMessage());
        }

        LOGGER.info("Result** "+ listOfArticles.toString());
        return listOfArticles.getArticles();
    }

    @GetMapping("/findArticle")
    public Article[] findArticle(@RequestParam(required = false) String title,
                                 @RequestParam(required = false) String author) {

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(author)){
            throw new MissingParameterException(" At least one parameter is required \n title::" + title + " " + " author:" + author);
        }
        SearchResponse listOfArticles = getArticleByTitle(title);

        LOGGER.info("Result** "+ listOfArticles.toString());
        return listOfArticles.getArticles();
    }

    private SearchResponse getArticleByTitle(String title) {
        String API_KEY = appConfig.getApikey();

        String url= "https://gnews.io/api/v4/search?q=example&apikey="+API_KEY ;
        RestTemplate restTemplate = new RestTemplate();
        SearchResponse listOfArticles= null;
        try {
            listOfArticles = restTemplate.getForObject(url, SearchResponse.class);
        }catch (Exception e){
            LOGGER.error("error while calling GNews" + e.getMessage());
        }
        return listOfArticles;
    }

    @GetMapping("/search-title")
    public Article[] searchByKeyword(@RequestParam(required = true) String title){

        String API_KEY = appConfig.getApikey();

        if(StringUtils.isEmpty(title)){
            throw new MissingParameterException(" searchString::" + title);
        }
        SearchResponse listOfArticles = trueNewsServiceImpl.getSearchResponseByTitle(title);

        LOGGER.info("Result** "+ listOfArticles.toString());
        return listOfArticles.getArticles();
    }

    @GetMapping("/search")
    @Cacheable(value="ArticleByTitleCache", key="#searchString")
    public Article[] searchByKeyword(@RequestParam(required = true) String searchString,
                                     @RequestParam(required = true) String numberOfArticles) {

        if(StringUtils.isEmpty(searchString) || StringUtils.isEmpty(numberOfArticles)){
            throw new MissingParameterException(" searchString::" + searchString + " " + " numberOfArticles:" + numberOfArticles);
        }
        SearchResponse listOfArticles = trueNewsServiceImpl.getSearchResponse(searchString, numberOfArticles);

        LOGGER.info("Result** "+ listOfArticles.toString());
        return listOfArticles.getArticles();
    }
}
