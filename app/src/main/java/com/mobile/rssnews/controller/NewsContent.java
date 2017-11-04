package com.mobile.rssnews.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewsContent {
    private static final String BASE_PATH = "http://feeds.bbci.co.uk/news/";
    public static final String WORLD_NEWS = BASE_PATH + "world/rss.xml";
    public static final String BUSINESS_NEWS = BASE_PATH + "business/rss.xml";
    public static final String TECH_NEWS = BASE_PATH + "technology/rss.xml";
    public static final String SCIENCE_NEWS = BASE_PATH + "science_and_environment/rss.xml";
    public static final String ENT_AND_ARTS_NEWS = BASE_PATH + "entertainment_and_arts/rss.xml";

    public static void getContent(int pageNumber) {
        NewsContent content = new NewsContent();
        try {
            switch (pageNumber) {
                case 0:
                    content.downloadContent(new URL(WORLD_NEWS));
                    return;
                case 1:
                    content.downloadContent(new URL(BUSINESS_NEWS));
                    return;
                case 2:
                    content.downloadContent(new URL(TECH_NEWS));
                    return;
                case 3:
                    content.downloadContent(new URL(SCIENCE_NEWS));
                    return;
                case 4:
                    content.downloadContent(new URL(ENT_AND_ARTS_NEWS));
                    return;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void downloadContent(URL url) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
