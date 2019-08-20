package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.ItemClickListener;
import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.model.modelpostofcate.PostOfCate;

import java.util.ArrayList;
import java.util.List;

public class AdapterListPost extends RecyclerView.Adapter<AdapterListPost.ViewHolder> {

    private ItemClickListener mitemClickRv;
    private List<PostOfCate> postOfCates;
    private Context context;

    public AdapterListPost(List<PostOfCate> postOfCates, Context context, ItemClickListener itemClickRv) {
        this.postOfCates = postOfCates;
        this.context = context;
        mitemClickRv = itemClickRv;
    }



    @NonNull
    @Override
    public AdapterListPost.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        PostOfCate postOfCate = postOfCates.get(i);
        viewHolder.tvTitle.setText(postOfCate.getTitle().getRendered());


        final int id = postOfCate.getId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitemClickRv.onClick(i,id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postOfCates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView banner;
        private TextView tvTitle;
        private TextView tvCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.imgBanner);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCount = itemView.findViewById(R.id.tvslcate);
        }
    }

    public void updateData(List<PostOfCate> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        postOfCates.addAll(data);
        notifyDataSetChanged();
    }
}
