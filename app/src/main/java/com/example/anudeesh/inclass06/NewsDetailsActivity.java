package com.example.anudeesh.inclass06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewsDetailsActivity extends AppCompatActivity {

    News newsitem;
    private TextView title, date, desc;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        newsitem = new News();
        newsitem = (News) getIntent().getExtras().getSerializable(MainActivity.NEWS_KEY);
        title = (TextView) findViewById(R.id.textViewStoryTitle);
        date = (TextView) findViewById(R.id.textViewPubDate);
        desc = (TextView) findViewById(R.id.textViewDescription);
        img = (ImageView) findViewById(R.id.imageView);
        /*String myFormat = "MM/DD/YYYY HH:MM AAA";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date dateObj = sdf.parse(newsitem.getDate());
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(dateObj);*/
        String data = newsitem.getDescription();
        data = data.replaceAll("<(.*?)\\>"," ");
        data = data.replaceAll("<(.*?)\\\n"," ");
        data = data.replaceFirst("(.*?)\\>", " ");
        data = data.replaceAll("&nbsp;"," ");
        data = data.replaceAll("&amp;"," ");
//        Log.d("deb",data);
        title.setText(newsitem.getTitle());
//        Log.d("demo",newsitem.getTitle());
        date.setText(newsitem.getDate());
        desc.setText(data);
        Picasso.with(this)
                .load(newsitem.getMainurl())
                .into(img);
    }
}
