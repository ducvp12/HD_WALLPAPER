package com.example.asshoanthien.dnhonthin.model;

public class ModelFavorites {
    private int id;
    private String imgContent;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelFavorites(String imgContent) {
        this.id=id;
        this.imgContent = imgContent;

    }

    public String getImgContent() {
        return imgContent;
    }


}
