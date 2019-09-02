package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.ImageDetailActivity;
import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.model.modelex.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterLatest extends RecyclerView.Adapter<AdapterLatest.ViewHolder> {
    private List<Photo> modelLatestList;
    Context context;
    public AdapterLatest(List<Photo> modelLatestList, Context context) {
        this.modelLatestList = modelLatestList;
        this.context = context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_latest, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
       context = viewHolder.itemView.getContext();
//        ModelLatest md = modelLatestList.get(i);
//        Post mdd = load.get(i);
//        viewHolder.tvCOuntHeart.setText(md.getTvCountHeart());
//        viewHolder.tvCountEye.setText(md.getTvCountEye());
        if (modelLatestList !=null) {
            Photo post = modelLatestList.get(i);
            Picasso.with(context).load(post.getUrlM()).into(viewHolder.imgContent);
            Log.e("lala",post.getUrlM()+"");
            Toast.makeText(context, post.getUrlM()+"", Toast.LENGTH_SHORT).show();
        }

        //                      .getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getMediumLarge().getSourceUrl()
//        Log.e("img", ""+ load.get(i).getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getLarge().getSourceUrl());

//        load.get(i).getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getLarge();

        viewHolder.imgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ImageDetailActivity.class);
                intent.putExtra("position",i);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelLatestList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;
        ImageView imgIconEye;
        ImageView imgIconHeart;
        TextView tvCountEye;
        TextView tvCOuntHeart;


        ViewHolder(View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.imgContent);
            imgIconEye = itemView.findViewById(R.id.imgIconEye);
            imgIconHeart = itemView.findViewById(R.id.imgIconHeart);
            tvCountEye = itemView.findViewById(R.id.tvCountEye);
            tvCOuntHeart = itemView.findViewById(R.id.tvCountHeart);

        }
    }

}
