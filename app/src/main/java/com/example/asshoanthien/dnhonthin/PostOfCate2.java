package com.example.asshoanthien.dnhonthin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.adapter.AdapterListPost;
import com.example.asshoanthien.dnhonthin.model.modelpostofcate.PostOfCate;
import com.example.asshoanthien.dnhonthin.retrofit.Hdwallpaper_Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostOfCate2 extends AppCompatActivity implements ItemClickListener {
    private RecyclerView mRvListPost;
    private List<com.example.asshoanthien.dnhonthin.model.modelpostofcate.PostOfCate> postOfCates= new ArrayList<>();
    private AdapterListPost adapterListPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_of_cate2);
        mRvListPost = findViewById(R.id.rvListPost2);
        adapterListPost = new AdapterListPost(postOfCates, PostOfCate2.this, new ItemClickListener() {
            @Override
            public void onClick(int i, int id) {
                Intent intent=new Intent(PostOfCate2.this, MediaOfPostActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

        });
        mRvListPost.setAdapter(adapterListPost);
        mRvListPost.setLayoutManager(new LinearLayoutManager(PostOfCate2.this));
        Intent intent=getIntent();
       int i= intent.getIntExtra("id",0);

        Hdwallpaper_Retrofit.getInstance().getPostOfCate(i, 5, 1).enqueue(new Callback<List<PostOfCate>>() {
            @Override
            public void onResponse(Call<List<PostOfCate>> call, Response<List<PostOfCate>> response) {
                if (response.body() != null) {
                    adapterListPost.updateData(response.body());
                    Log.e("listPost", "onResponse: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PostOfCate>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(int i, int id) {

    }

    public void back(View view) {

        finish();
    }
}
