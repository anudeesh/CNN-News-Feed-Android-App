package com.example.anudeesh.inclass06;

import java.io.Serializable;

/**
 * Created by Anudeesh on 9/27/2016.
 */
public class News implements Serializable {
    String title, description, date, thumburl, mainurl;

    public News(String title, String description, String date, String thumburl, String mainurl) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.thumburl = thumburl;
        this.mainurl = mainurl;
    }

    public News() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getMainurl() {
        return mainurl;
    }

    public void setMainurl(String mainurl) {
        this.mainurl = mainurl;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", thumburl='" + thumburl + '\'' +
                ", mainurl='" + mainurl + '\'' +
                '}';
    }
}
