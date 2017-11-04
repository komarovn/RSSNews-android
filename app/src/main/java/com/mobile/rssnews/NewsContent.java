package com.mobile.rssnews;

public class NewsContent {
    private static final String BASE_PATH = "http://feeds.bbci.co.uk/news/";
    public static final String WORLD_NEWS = BASE_PATH + "world/rss.xml";
    public static final String BUSINESS_NEWS = BASE_PATH + "business/rss.xml";
    public static final String TECH_NEWS = BASE_PATH + "technology/rss.xml";
    public static final String SCIENCE_NEWS = BASE_PATH + "science_and_environment/rss.xml";
    public static final String ENT_AND_ARTS_NEWS = BASE_PATH + "entertainment_and_arts/rss.xml";

    public static void getContent(int pageNumber) {
        switch (pageNumber) {
            case 1:
                return;
            case 2:
                return;
            case 3:
                return;
            case 4:
                return;
            case 5:
                return;
        }
    }
}
