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

import java.util.Optional;

@RestController
// @Service("/truenews")
public class TrueNewsService    {

    Log LOGGER= LogFactory.getLog(TrueNewsService.class);


    @Autowired
    AppConfig appConfig;

    @Autowired
    TrueNewsServiceImpl trueNewsServiceImpl;

    @GetMapping("/search-title")
    @Cacheable(value="ArticleByTitleCache", key="#title")
    public Article[] searchByKeyword(@RequestParam(required = true) String title){

        String API_KEY = appConfig.getApikey();

        if(StringUtils.isEmpty(title)){
            throw new MissingParameterException(" searchString::" + title);
        }
        SearchResponse listOfArticles = trueNewsServiceImpl.getSearchResponseByTitle(title);

        return Optional.ofNullable(listOfArticles)
                .map(articles -> {
                    LOGGER.info("Result: " + articles);
                    return articles.getArticles();
                })
                .orElse(null);
    }

    @GetMapping("/search")
    @Cacheable(value="ArticleByTitleCache", key="#searchString")
    public Article[] searchByKeyword(@RequestParam(required = true) String searchString,
                                     @RequestParam(required = true) String numberOfArticles) {

        if(StringUtils.isEmpty(searchString) || StringUtils.isEmpty(numberOfArticles)){
            throw new MissingParameterException(" searchString::" + searchString + " " + " numberOfArticles:" + numberOfArticles);
        }
        SearchResponse listOfArticles = trueNewsServiceImpl.getSearchResponse(searchString, numberOfArticles);

        return Optional.ofNullable(listOfArticles)
                .map(articles -> {
                    LOGGER.info("Result: " + articles);
                    return articles.getArticles();
                })
                .orElse(null);

    }
}
