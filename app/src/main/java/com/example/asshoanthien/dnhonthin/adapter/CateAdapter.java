package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.LatestActivity;
import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.model.model_cate.Category;

import java.util.List;

public class CateAdapter extends RecyclerView.Adapter<CateHolder> {
    private Context context;
    private List<Category> cateList;

    public CateAdapter(Context context, List<Category> cateList) {
        this.context = context;
        this.cateList = cateList;
    }

    @NonNull
    @Override
    public CateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new CateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CateHolder holder, int position) {
        Category category= cateList.get(position);
        holder.tvContent.setText(category.getName());
        holder.tvsl.setText("("+category.getCount()+")");
        holder.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, LatestActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }

}
