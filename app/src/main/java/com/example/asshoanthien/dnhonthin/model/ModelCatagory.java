package com.example.asshoanthien.dnhonthin.model;

public class ModelCatagory {
    private String imgBanner;
    private String tvTitle;

    public ModelCatagory(String imgBanner, String tvTitle) {
        this.imgBanner = imgBanner;
        this.tvTitle = tvTitle;
    }

    public String getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(String imgBanner) {
        this.imgBanner = imgBanner;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }
}
