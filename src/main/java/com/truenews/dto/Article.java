package com.truenews.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Calendar;
// @JsonProperty
public class Article implements Serializable {
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publisehdAt;
    private Source source;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublisehdAt() {
        return publisehdAt;
    }

    public void setPublisehdAt(Calendar publisehdAt) {

        this.publisehdAt = String.valueOf(publisehdAt);
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", publisehdAt='" + publisehdAt + '\'' +
                ", source=" + source +
                '}';
    }
}
