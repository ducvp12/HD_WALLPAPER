package com.example.asshoanthien.dnhonthin;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asshoanthien.dnhonthin.model.modelLatest.Latestt;
import com.example.asshoanthien.dnhonthin.retrofit.Hdwallpaper_Retrofit;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDetailActivity extends AppCompatActivity {

    private ImageView mImgBack;
    private FloatingActionMenu mFabMenu;
    private ImageView mImgDetail;
    List<Latestt> posts;
    Bitmap bm;
    private ImageView imgBack;
    private ImageView imgDetail;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton btnsetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        initView();
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Intent intent = getIntent();



        final int position = intent.getIntExtra("position", 0);
        Hdwallpaper_Retrofit.getInstance().getSourceUrl().enqueue(new Callback<List<Latestt>>() {
            @Override
            public void onResponse(Call<List<Latestt>> call, final Response<List<Latestt>> response) {
                final String source = response.body().get(position).getGuid().getRendered();
                Log.e("22323", "onResponse: " + source);
                Picasso.with(ImageDetailActivity.this).load(source).into(mImgDetail);
            }
            @Override
            public void onFailure(Call<List<Latestt>> call, Throwable t) {
            }
        });


//        String source = posts.get(position).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
//        Log.e("source", " "+source );
//
//
//
//        mImgDetail.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        // check to see if the file exists
//        File file = new File(source);
//        if (file.exists()){
//
//            bm = BitmapFactory.decodeFile(source);
//        }
//
//
//        // set the image and text
//        mImgDetail.setImageBitmap(bm);
    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mFabMenu = findViewById(R.id.fab_menu);
        mImgDetail = findViewById(R.id.imgDetail);
        btnsetas = (FloatingActionButton) findViewById(R.id.btnsetas);
        btnsetas.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    myWallpaperManager.setResource(R.drawable.haha);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//        btnsetas.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onClick(View view) {
//                WallpaperManager myWallpaperManager
//                        = WallpaperManager.getInstance(getApplicationContext());
//                try {
//                    myWallpaperManager.setResource(R.id.mImgDetail);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });
    }


    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}

