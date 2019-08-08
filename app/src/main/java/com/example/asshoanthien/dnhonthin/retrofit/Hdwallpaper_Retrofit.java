package com.example.asshoanthien.dnhonthin.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Hdwallpaper_Retrofit {
    public static HdwallpaperService hdwallpaperService;
    public static HdwallpaperService getInstance(){
        if (hdwallpaperService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://asian.dotplays.com")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            hdwallpaperService=retrofit.create(HdwallpaperService.class);
        }
        return hdwallpaperService;
    }
}
