package com.example.lilcare.Models;


public class fileModel {

    String title,date,vUrl;

    public fileModel() {}

    public fileModel(String title, String date, String vUrl){
        this.title = title;
        this.vUrl = vUrl;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getvUrl() {
        return vUrl;
    }

    public void setvUrl(String vUrl) {
        this.vUrl = vUrl;
    }
}

