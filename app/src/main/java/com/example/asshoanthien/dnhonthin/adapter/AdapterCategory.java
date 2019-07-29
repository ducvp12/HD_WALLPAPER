package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.ItemClickListener;
import com.example.asshoanthien.dnhonthin.LatestActivity;
import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.model.ModelCatagory;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    private List<ModelCatagory> modelCatagoryList;

    Context context;
    public AdapterCategory(List<ModelCatagory> modelCatagoryList, Context context) {
        this.modelCatagoryList = modelCatagoryList;
        this.context=context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =  inflater.inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelCatagory md = modelCatagoryList.get(i);
        viewHolder.tvTitle.setText(md.getTvTitle());
        viewHolder.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, LatestActivity.class);
                context.startActivity(intent);
            }
        });
      viewHolder.setItemClickListener(new ItemClickListener() {
          @Override
          public void onClick(View view, int position, boolean isLongClick) {
              if(isLongClick)
                  Toast.makeText(context, "Long Click: ", Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(context, " "+position, Toast.LENGTH_SHORT).show();
              Intent intent=new Intent(context, LatestActivity.class);
              context.startActivity(intent);

          }
      });
    }

    @Override
    public int getItemCount() {
        return modelCatagoryList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private ImageView banner;
        private TextView tvTitle;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = (ImageView) itemView.findViewById(R.id.imgBanner);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }

            public void setItemClickListener(ItemClickListener itemClickListener) {
                this.itemClickListener = itemClickListener;
            }
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(view,getAdapterPosition(),false);
            }

            @Override
            public boolean onLongClick(View view) {
                itemClickListener.onClick(view,getAdapterPosition(),true);
                return true;

        }
    }
}
