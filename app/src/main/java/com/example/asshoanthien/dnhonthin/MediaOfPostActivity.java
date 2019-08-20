package com.example.asshoanthien.dnhonthin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.adapter.AdapterMediaOfPost;
import com.example.asshoanthien.dnhonthin.model.modelmediaofpost.MediaOfPost;
import com.example.asshoanthien.dnhonthin.retrofit.Hdwallpaper_Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaOfPostActivity extends AppCompatActivity {
    private RecyclerView mRvMedia;




    private List<MediaOfPost> mediaOfPosts= new ArrayList<>();
    private AdapterMediaOfPost adapterMediaOfPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_of_post);
        mRvMedia = findViewById(R.id.rvMedia);
        adapterMediaOfPost = new AdapterMediaOfPost(mediaOfPosts);
        mRvMedia.setAdapter(adapterMediaOfPost);
        mRvMedia.setLayoutManager(new GridLayoutManager(MediaOfPostActivity.this,2));

        Intent intent=getIntent();
        int i=intent.getIntExtra("id",0);
        Hdwallpaper_Retrofit.getInstance().getMediaOfPost(i).enqueue(new Callback<List<MediaOfPost>>() {
            @Override
            public void onResponse(Call<List<MediaOfPost>> call, Response<List<MediaOfPost>> response) {
                adapterMediaOfPost.setLoads(response.body());
                Log.e("AAA", "onResponse: "+ response.toString());
                Log.e("mediaSize", "onResponse: " + mediaOfPosts.size() );
                //String a = mediaOfPosts.get(0).getSourceUrl();
                //Log.e("url", "onResponse:" + a);
            }

            @Override
            public void onFailure(Call<List<MediaOfPost>> call, Throwable t) {

            }
        });
    }

    public void back1(View view) {
finish();
    }
}
