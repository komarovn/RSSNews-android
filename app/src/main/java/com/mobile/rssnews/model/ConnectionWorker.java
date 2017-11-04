package com.mobile.rssnews.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnectionWorker extends AsyncTask<String, Void, List<NewsItem>> {

    @Override
    protected List<NewsItem> doInBackground(String... params) {
        List<NewsItem> items = new ArrayList<>();

        try {
            URL url = new URL(params[0]);
            String content = getContent(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    private String getContent(URL url) {
        String content = "";
        BufferedReader reader = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            final StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            content = sb.toString();

            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    private Bitmap getImage(URL url) {
        Bitmap image = null;
        try {
            InputStream input = url.openStream();
            image = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    
}
