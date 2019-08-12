package com.example.asshoanthien.dnhonthin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.adapter.AdapterMediaOfPost;
import com.example.asshoanthien.dnhonthin.model.modelmediaofpost.MediaOfPost;
import com.example.asshoanthien.dnhonthin.retrofit.Hdwallpaper_Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMediaOfPost extends Fragment implements ItemClickListener {

    private RecyclerView mRvMedia;

    public static final String KEY_POSITION = "FragmentPostOfCate.KEY_POSITION";
    public static final String KEY_ID = "FragmentPostOfCate.KEY_ID";


    private List<MediaOfPost> mediaOfPosts;
    private AdapterMediaOfPost adapterMediaOfPost;

    private int id;
    private int position;

    public FragmentMediaOfPost() {
        mediaOfPosts = new ArrayList<>();
    }

    // fun này tạo ra để hứng dữ liệu
    // ở đây có 2 param là id với position

    public static FragmentMediaOfPost newInstanceMedia(int id, int position) {
        // Bundle dùng để đóng gói dữ liệu (d1: khởi tạo bundle)
        Bundle args = new Bundle();

        // đóng gói id vs position để chuyển
        // có 2 param, param1 là key, param2 là dữ liệu
        args.putInt(KEY_ID, id);
        args.putInt(KEY_POSITION, position);

        // Giờ mới khởi tạo fragment FragmentPostOfCate
        FragmentMediaOfPost fragment = new FragmentMediaOfPost();
        // setArgument ở đây giống như intent
        // id vs position như 1 món phụ kiện
        // Bundle giống như thùng hàng
        // Argument như 1 phương tiện vận chuyển thùng hàng (bên trong có phụ kiện)
        fragment.setArguments(args);
        // khi xong hết rồi mới chạy vào contractor rồi mới đến vòng đời của fragment
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_of_post, container, false);
        // fun này giống setcontenview ở bên act có nhiệm vụ nhúng giao diện
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // sau đó chạy đến fun này
        initView(view);
        initData();
        setupAdapter();
    }

    private void setupAdapter() {
        adapterMediaOfPost = new AdapterMediaOfPost(mediaOfPosts);
        mRvMedia.setAdapter(adapterMediaOfPost);
        mRvMedia.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    private void initData() {
        // ở đây mình sẽ kiểm tra xem có tín hiệu vận chuyển dữ liệu gì không?
        if(getArguments() != null){
            // nếu có thì lấy ra id vs postion theo cái key
            id = getArguments().getInt(KEY_ID);
            position = getArguments().getInt(KEY_POSITION);
        }
        Hdwallpaper_Retrofit.getInstance().getMediaOfPost(id).enqueue(new Callback<List<MediaOfPost>>() {
            @Override
            public void onResponse(Call<List<MediaOfPost>> call, Response<List<MediaOfPost>> response) {
                adapterMediaOfPost.setLoads(response.body());
                Log.e("AAA", "onResponse: "+ response.toString());
                Log.e("mediaSize", "onResponse: " + mediaOfPosts.size() );
                String a = mediaOfPosts.get(0).getSourceUrl();
                Log.e("url", "onResponse:" + a);
            }

            @Override
            public void onFailure(Call<List<MediaOfPost>> call, Throwable t) {

            }
        });
    }


    private void initView(View view) {

        mRvMedia = view.findViewById(R.id.rvMedia);
    }

    @Override
    public void onClick(int view, int position) {

    }
}
