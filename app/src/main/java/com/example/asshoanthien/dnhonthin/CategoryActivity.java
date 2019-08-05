package com.example.asshoanthien.dnhonthin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.adapter.AdapterCategory;
import com.example.asshoanthien.dnhonthin.model.model_embed.WpFeaturedmedium_;
import com.example.asshoanthien.dnhonthin.retrofit.Hdwallpaper_Retrofit;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int _embedded;
    LinearLayoutManager linearLayoutManager;
    private RecyclerView mRvCate;
    private List<WpFeaturedmedium_> wpFeaturedmedium_list;


    private AdapterCategory adapterCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mRvCate = findViewById(R.id.rv);
        wpFeaturedmedium_list = new ArrayList<>();
        adapterCategory = new AdapterCategory(wpFeaturedmedium_list,this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRvCate.setHasFixedSize(true);
        mRvCate.setLayoutManager(manager);

        Hdwallpaper_Retrofit.getInstance().getCate(_embedded).enqueue(new Callback<List<WpFeaturedmedium_>>() {
            @Override
            public void onResponse(Call<List<WpFeaturedmedium_>> call, Response<List<WpFeaturedmedium_>> response) {
                wpFeaturedmedium_list=response.body();
                adapterCategory=new AdapterCategory(wpFeaturedmedium_list,CategoryActivity.this);
                Log.e("ahahaa", response.body().toString());
                mRvCate.setAdapter(adapterCategory);
            }

            @Override
            public void onFailure(Call<List<WpFeaturedmedium_>> call, Throwable t) {

            }
        });

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
            // Handle the camera action
            Intent intent=new Intent(CategoryActivity.this,LatestActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_more) {
            Intent intent=new Intent(CategoryActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_heart) {
            Intent intent=new Intent(CategoryActivity.this,MyFavoryitesActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_about_us) {
            Intent intent=new Intent(CategoryActivity.this,AboutUsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_more) {
            Intent intent=new Intent(CategoryActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



//    public void getData(int _embedded){
//        Hdwallpaper_Retrofit.getInstance().getCate(_embedded)
//                .enqueue(new Callback<List<WpFeaturedmedium_>>() {
//                    @Override
//                    public void onResponse(Call<List<WpFeaturedmedium_>> call, Response<List<WpFeaturedmedium_>> response) {
//                        Toast.makeText(CategoryActivity .this, response.body().size() + "", Toast.LENGTH_SHORT).show();
//
//                       wpFeaturedmedium_list=response.body();
//                       adapterCategory=new AdapterCategory(wpFeaturedmedium_list,CategoryActivity.this);
//                       mRvCate.setAdapter(adapterCategory);
//                        adapterCategory.notifyDataSetChanged();
//                        Log.e("haha",response.body()+"");
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<WpFeaturedmedium_>> call, Throwable t) {
//
//                    }
//                });
//
//
//    }


//    private void writeRecycler(String response){
//
//        try {
//            JSONArray jsonArray = new JSONArray(response);
//            //ArrayList<category> exampleList = new ArrayList<>();
//
//
//            for(int i=0 ; i<jsonArray.length();i++){
//
//
//
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                JSONObject title = jsonObject.getJSONObject("title");
//
//                String rendered = title.getString("rendered");
//                Log.d("oo", String.valueOf(jsonArray));
//                Log.d("gggggg", String.valueOf(jsonObject));
//
//                JSONObject jsonObjectE = jsonObject.getJSONObject("_embedded");
//                Log.d("kk", String.valueOf(jsonObjectE));
//
//                JSONArray jsonArray1 = jsonObjectE.getJSONArray("wp:featuredmedia");
//                Log.d("hhhhhh", String.valueOf(jsonArray1));
//
//                for(int j=0 ; j<jsonArray1.length();j++) {
//                    JSONObject jsonObjectA = jsonArray1.getJSONObject(j);
//                    Log.d("cccc", String.valueOf(jsonObjectA));
//
//                    String source_url = jsonObjectA.getString("source_url");
//
//
//
//                    wpFeaturedmedium_list.add(new WpFeaturedmedium_(source_url));
//                    Log.d("sourcURL ", source_url);
//                    Log.d("fffff ", String.valueOf(wpFeaturedmedium_list));
//
//
//
//                }
//
//
//
//
//                mRvCate.setHasFixedSize(true);
//
//
//
//
//            }
//
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
}
