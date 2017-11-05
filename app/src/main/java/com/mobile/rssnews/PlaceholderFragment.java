package com.mobile.rssnews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.rssnews.controller.NewsContent;
import com.mobile.rssnews.controller.NewsWallAdapter;
import com.mobile.rssnews.model.NewsItem;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        List<NewsItem> data = NewsContent.getContent((Integer) this.getArguments().get(ARG_SECTION_NUMBER));

        View topNewsHolder = rootView.findViewById(R.id.topNewsHolder);

        if (data != null && !data.isEmpty()) {
            NewsItem story = data.get(0);
            ((TextView) topNewsHolder.findViewById(R.id.topNewsTitle)).setText(story.getTitle());
            ((TextView) topNewsHolder.findViewById(R.id.topNewsDescriprion)).setText(story.getDescription());
            ImageView topNewsImage = (ImageView) topNewsHolder.findViewById(R.id.topNewsImage);
            //topNewsImage.setMinimumHeight((int) (topNewsImage.getMeasuredWidth() / 1.77777778));
            //topNewsImage.setMaxHeight((int) (topNewsImage.getMeasuredWidth() / 1.77777778));
            topNewsImage.setImageBitmap(story.getImage());
            topNewsHolder.setTag(story.getLink());

            topNewsHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openWebView((String) v.getTag());
                }
            });
        }

        ListView newsWall = (ListView) rootView.findViewById(R.id.newsWall);
        NewsWallAdapter adapter = new NewsWallAdapter(data.subList(1, data.size() - 1), this);
        fixScrollListView(newsWall);
        setListViewHeightBasedOnChildren(newsWall);

        newsWall.setAdapter(adapter);

        return rootView;
    }

    public void openWebView(String link) {
        Intent intent = new Intent(getActivity(), WebBrowserActivity.class);
        intent.putExtra("link", link);
        startActivityForResult(intent, 0);
    }

    private void fixScrollListView(ListView lv) {
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
