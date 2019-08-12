package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.FragmentPostOfCate;
import com.example.asshoanthien.dnhonthin.ItemClickListener;
import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.model.model_cate.Category;

import java.util.List;

public class CateAdapter extends RecyclerView.Adapter<CateHolder> {
    private Context context;
    private List<Category> cateList;
    private ItemClickListener mitemClickRv;
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
    public void onBindViewHolder(@NonNull CateHolder holder, final int position) {
        Category category= cateList.get(position);
        holder.tvContent.setText(category.getName());
        holder.tvsl.setText("("+category.getCount()+")");
        final int id = category.getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FragmentPostOfCate.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }

}
