package com.example.anudeesh.inclass06;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ArrayList<News> newsArrayList = new ArrayList<News>();
    final static String NEWS_KEY = "NEWS";
    static LinearLayout lv;
    //ImageView thumb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (LinearLayout) findViewById(R.id.layout);

        if(isConnectedOnline()) {
            new GetNewsAsyncTask(this).execute("http://rss.cnn.com/rss/cnn_tech.rss");
        } else {
            Toast.makeText(MainActivity.this,"Cannot access internet",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void onTaskCompleted(ArrayList<News> alist) {
        if(alist!=null) {
            for (final News n : alist) {
                //LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //View v = inflater.inflate(R.layout.activity_main, null);
                //LinearLayout lv = (LinearLayout) v.findViewById(R.id.layout);

                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,0,10);
                //ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150));
                ll.setLayoutParams(params);
                ImageView iv = new ImageView(this);
                //Log.d("debug",n.getDate());
                Picasso.with(this)
                        .load(n.getThumburl())
                        .into(iv);
                LinearLayout.LayoutParams ivparams = new LinearLayout.LayoutParams(250,250);
                ivparams.setMargins(0,0,10,0);
                iv.setLayoutParams(ivparams);
                TextView tv = new TextView(this);
                tv.setText(n.getTitle());
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,250));
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setTypeface(Typeface.DEFAULT_BOLD);
                ll.addView(iv);
                ll.addView(tv);
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,NewsDetailsActivity.class);
                        intent.putExtra(NEWS_KEY,n);
                        startActivity(intent);
                    }
                });
                lv.addView(ll);
            }
        }
    }
}
