package com.example.asshoanthien.dnhonthin.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.R;

class CateHolder extends RecyclerView.ViewHolder {
    public TextView tvContent,tvsl;
    ImageView banner;


    public CateHolder(View view) {
        super(view);
        tvContent=view.findViewById(R.id.tvTitle);
        tvsl=view.findViewById(R.id.tvslcate);
        banner=view.findViewById(R.id.imgBanner);
    }
}
