package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.database.DAO;
import com.example.asshoanthien.dnhonthin.model.ModelFavorites;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.ViewHolder> {
    private List<ModelFavorites> modelFavoritesList;
    Context context;
    private DAO dao;

    public AdapterFavorites(List<ModelFavorites> modelFavoritesList, Context context) {
        this.modelFavoritesList = modelFavoritesList;
        this.context = context;
        dao = new DAO(context);
        dao.open();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelFavorites md = modelFavoritesList.get(i);
        Picasso.with(context).load(md.getImgContent()).into(viewHolder.imgContent);
    }

    @Override
    public int getItemCount() {
        return modelFavoritesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.imgContent);

        }
    }
}
