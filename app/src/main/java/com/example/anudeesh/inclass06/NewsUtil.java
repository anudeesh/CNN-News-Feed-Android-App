package com.example.anudeesh.inclass06;

import android.text.Html;
import android.util.Log;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Anudeesh on 9/27/2016.
 */
public class NewsUtil {
    static public class NewsPullParser extends DefaultHandler {
        static public ArrayList<News> parseNews(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            ArrayList<News> newsList = new ArrayList<News>();
            News news = null;
            int event = parser.getEventType();

            while(event!=XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG :
                        if(parser.getName().equals("item")) {
                            news = new News();
                        } else if(parser.getName().equals("title")) {
                            if(news!=null) {
                                news.setTitle(parser.nextText().trim());
                            }
                        } else if(parser.getName().equals("pubDate")) {
                            if(news!=null) {
                                news.setDate(parser.nextText().trim());
                                //Log.d("debug","Date is "+parser.nextText().trim());
                            }
                        }else if(parser.getName().equals("description")) {
                            if(news!=null) {
                                String data = parser.nextText().trim();
                                /*if(data.equals("") || data.isEmpty()) {
                                    news.setDescription("No description to show");
                                } else {*/
                                    news.setDescription(data);
                                //}
                            }
                        } else if(parser.getName().equals("media:content")) {
                            String h = parser.getAttributeValue(null,"height");
                            String w = parser.getAttributeValue(null,"width");
                            if(h.equals(w)) {
                                news.setThumburl(parser.getAttributeValue(null,"url"));
                            } else {
                                news.setMainurl(parser.getAttributeValue(null,"url"));
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")) {
                            newsList.add(news);
                            news=null;
                        }
                        break;
                    default:
                        break;
                }
                event = parser.next();
            }
            return newsList;
        }

    }
}
