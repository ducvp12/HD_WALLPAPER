package com.example.asshoanthien.dnhonthin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.ItemClickListener;
import com.example.asshoanthien.dnhonthin.R;
import com.example.asshoanthien.dnhonthin.model.model_cate.Category;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    private List<Category> modelWp;
    private ItemClickListener mitemClickRv;


    Context context;
    public AdapterCategory(List<Category> modelWp, Context context,ItemClickListener itemClickRv) {
        this.modelWp = modelWp;
        this.context=context;
        mitemClickRv=itemClickRv;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =  inflater.inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Category md = modelWp.get(i);


        viewHolder.tvTitle.setText(md.getName());
        viewHolder.tvsl.setText("("+md.getCount()+")");
        Log.e("kaka",md.getName()+"");
        final int id = md.getId();
        viewHolder.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitemClickRv.onClick(i,id);
            }
        });

//      viewHolder.setItemClickListener(new ItemClickListener() {
//          @Override
//          public void onClick(View view, int position, boolean isLongClick) {
//              if(isLongClick)
//                  Toast.makeText(context, "Long Click: ", Toast.LENGTH_SHORT).show();
//              else
//                  Toast.makeText(context, " "+position, Toast.LENGTH_SHORT).show();
//              Intent intent=new Intent(context, LatestActivity.class);
//              context.startActivity(intent);
//
//          }
//      });
    }

    @Override
    public int getItemCount() {
        return modelWp.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView banner;
        private TextView tvTitle,tvsl;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = (ImageView) itemView.findViewById(R.id.imgBanner);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvsl=itemView.findViewById(R.id.tvslcate);
        }


    }
}
