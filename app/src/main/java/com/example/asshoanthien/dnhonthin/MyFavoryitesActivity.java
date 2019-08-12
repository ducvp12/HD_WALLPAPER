package com.example.asshoanthien.dnhonthin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.adapter.AdapterFavorites;
import com.example.asshoanthien.dnhonthin.database.DAO;
import com.example.asshoanthien.dnhonthin.model.ModelFavorites;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MyFavoryitesActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener{


    private RecyclerView mRvFavorites;
    private List<ModelFavorites> modelFavoritesList;
    private AdapterFavorites adapterFavorites;
    private DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favoryites);
        dao=new DAO(this);
        dao.open();
        mRvFavorites=findViewById(R.id.rvlatest);
        modelFavoritesList = new ArrayList<>();
        modelFavoritesList=dao.getData();
        mRvFavorites.setLayoutManager(new GridLayoutManager(this, 2));
        adapterFavorites = new AdapterFavorites(modelFavoritesList,this);
        mRvFavorites.setAdapter(adapterFavorites);





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

        } else if (id == R.id.nav_category) {
            Intent intent=new Intent(MyFavoryitesActivity.this,CategoryActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_GIFs) {

        } else if (id == R.id.nav_about_us) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}