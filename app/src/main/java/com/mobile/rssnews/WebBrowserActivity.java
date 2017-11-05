package com.mobile.rssnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

public class WebBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_full_article);
        String url = getIntent().getStringExtra("link");

        WebView browser = (WebView) findViewById(R.id.newsFullArticleView);
        browser.loadUrl(url);
    }

    public void onClosingFullArticle(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}
