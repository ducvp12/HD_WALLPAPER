package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.ImageDetailActivity2;
import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.model.modelmediaofpost.MediaOfPost;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMediaOfPost extends RecyclerView.Adapter<AdapterMediaOfPost.ViewHolder> {

    List<MediaOfPost> mediaOfPosts;

    public AdapterMediaOfPost(List<MediaOfPost> mediaOfPosts) {
        this.mediaOfPosts = mediaOfPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_latest,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Context context = viewHolder.itemView.getContext();
        final MediaOfPost mediaOfPost2 = mediaOfPosts.get(i);

        if (mediaOfPosts!=null){
            Picasso.with(context).load(mediaOfPost2.getSourceUrl()).into(viewHolder.imageView);
            Log.e("juju",mediaOfPost2.getSourceUrl()+"");
            Log.e("juju2",mediaOfPost2+"");
        }
//
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageDetailActivity2.class);
                intent.putExtra("position",mediaOfPost2.getSourceUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mediaOfPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgContent);
        }
    }

    public void setLoads(List<MediaOfPost> data){
        if (data == null) {
            data = new ArrayList<>();
        }
        mediaOfPosts.addAll(data);
        notifyDataSetChanged();
    }
}
