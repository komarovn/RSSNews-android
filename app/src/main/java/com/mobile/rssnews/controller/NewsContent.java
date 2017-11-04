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

    public static void getContent(int pageNumber) {
        NewsContent content = new NewsContent();
        switch (pageNumber) {
            case 0:
                content.downloadContent(WORLD_NEWS);
                return;
            case 1:
                content.downloadContent(BUSINESS_NEWS);
                return;
            case 2:
                content.downloadContent(TECH_NEWS);
                return;
            case 3:
                content.downloadContent(SCIENCE_NEWS);
                return;
            case 4:
                content.downloadContent(ENT_AND_ARTS_NEWS);
                return;
        }
    }

    public void downloadContent(String url) {
        try {
            List<NewsItem> content = new ConnectionWorker().execute(url).get();
            int y = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
