package com.mobile.rssnews.controller;

import com.mobile.rssnews.model.ConnectionWorker;
import com.mobile.rssnews.model.NewsItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsContent {
    private static final String BASE_PATH = "http://feeds.bbci.co.uk/news/";
    public static final String WORLD_NEWS = BASE_PATH + "world/rss.xml";
    public static final String BUSINESS_NEWS = BASE_PATH + "business/rss.xml";
    public static final String TECH_NEWS = BASE_PATH + "technology/rss.xml";
    public static final String SCIENCE_NEWS = BASE_PATH + "science_and_environment/rss.xml";
    public static final String ENT_AND_ARTS_NEWS = BASE_PATH + "entertainment_and_arts/rss.xml";

    public static List<NewsItem> getContent(int pageNumber) {
        NewsContent content = new NewsContent();
        switch (pageNumber) {
            case 0:
                return content.downloadContent(WORLD_NEWS);
            case 1:
                return content.downloadContent(BUSINESS_NEWS);
            case 2:
                return content.downloadContent(TECH_NEWS);
            case 3:
                return content.downloadContent(SCIENCE_NEWS);
            case 4:
                return content.downloadContent(ENT_AND_ARTS_NEWS);
        }
        return null;
    }

    public List<NewsItem> downloadContent(String url) {
        try {
            return new ConnectionWorker().execute(url).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
