package com.example.asshoanthien.dnhonthin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.asshoanthien.dnhonthin.adapter.AdapterLatest;
import com.example.asshoanthien.dnhonthin.model.modelex.Photo;
import com.example.asshoanthien.dnhonthin.retrofit.Hdwallpaper_Retrofit;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
int page=1;
int per_page=10;
    private RecyclerView mRvCate;
     List<Photo> modelLatests;
    private AdapterLatest adapterLatest;
    SwipeRefreshLayout f5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);

        f5 = (SwipeRefreshLayout) findViewById(R.id.f52);
        mRvCate=findViewById(R.id.rvlatests2);
        modelLatests=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        adapterLatest = new AdapterLatest(modelLatests,LatestActivity.this);
        mRvCate.setAdapter(adapterLatest);
        mRvCate.setLayoutManager((new GridLayoutManager(this, 2)));

        f5.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //lay du lieu
                page=1;
                modelLatests.clear();
                getData2(page,per_page);
            }
        });

        mRvCate.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                page=page+1;
                getData2(page,per_page);
            }
        });


//
//        for (int i = 0; i < 40; i++) {
//            modelLatestList.add(new ModelLatest( "", "", i + "", i + ""));
//        }
//
//        Log.e("size", load.size() + "");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void  getData2 (int page, int per_page){

        Hdwallpaper_Retrofit.getInstance().getEx(page,per_page).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                Log.e("code", "" + response.code());
                if (response.code() == 200) {
                    if (response.body() == null) return;
                    modelLatests.addAll(response.body());
                    adapterLatest.notifyDataSetChanged();
                    Toast.makeText(LatestActivity.this, "hahaha", Toast.LENGTH_SHORT).show();
                    f5.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.category, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_latest) {

        } else if (id == R.id.nav_category) {
            Intent intent=new Intent(LatestActivity.this,CategoryActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_heart) {
            Intent intent=new Intent(LatestActivity.this,MyFavoryitesActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_about_us) {
            Intent intent=new Intent(LatestActivity.this,AboutUsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_more) {
            Intent intent=new Intent(LatestActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void getData(int embed){
//        Hdwallpaper_Retrofit.getInstance().getCate(embed)
//                .enqueue(new Callback<List<Latestt>>() {
//                    @Override
//                    public void onResponse(Call<List<Latestt>> call, Response<List<Latestt>> response) {
//                        Toast.makeText(LatestActivity.this, response.body().size() + "", Toast.LENGTH_SHORT).show();
//
//                        modelLatests.addAll(response.body());
//                        adapterLatest.notifyDataSetChanged();
//                        Log.e("hahahaha",modelLatests+"");
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Latestt>> call, Throwable t) {
//
//                    }
//                });
//
//    }
}