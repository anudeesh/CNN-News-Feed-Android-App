package com.example.anudeesh.inclass06;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Anudeesh on 9/27/2016.
 */
public class GetNewsAsyncTask extends AsyncTask<String,Void,ArrayList<News>> {
    MainActivity activity;
    //Activity a;
    ProgressDialog progressDialog;

    public GetNewsAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

   /* private GetNewsAsyncTask(Activity a) {
        this.a = a;
    }*/

    @Override
    protected ArrayList<News> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int statuscode = con.getResponseCode();

            if(statuscode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                return NewsUtil.NewsPullParser.parseNews(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading News ...");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<News> newses) {
        super.onPostExecute(newses);
        progressDialog.dismiss();
        activity.onTaskCompleted(newses);
    }


}
