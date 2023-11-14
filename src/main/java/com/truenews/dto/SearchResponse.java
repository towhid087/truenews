package com.truenews.dto;

import java.util.Arrays;

public class SearchResponse {
    private int totalArticles;
    private Article[] articles;

    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int totalArticles) {
        this.totalArticles = totalArticles;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "totalArticles=" + totalArticles +
                ", listOfArticles=" + Arrays.toString(articles) +
                '}';
    }
}
