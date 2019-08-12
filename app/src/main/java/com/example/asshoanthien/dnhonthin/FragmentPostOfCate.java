package com.example.asshoanthien.dnhonthin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asshoanthien.dnhonthin.adapter.AdapterListPost;
import com.example.asshoanthien.dnhonthin.model.modelpostofcate.PostOfCate;
import com.example.asshoanthien.dnhonthin.retrofit.Hdwallpaper_Retrofit;
import com.example.asshoanthien.dnhonthin.utills.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPostOfCate extends Fragment implements ItemClickListener {
    public static final String KEY_POSITION = "FragmentPostOfCate.KEY_POSITION";
    public static final String KEY_ID = "FragmentPostOfCate.KEY_ID";

    private ImageView mImgBack;
    private RecyclerView mRvListPost;
    private List<PostOfCate> postOfCates;
    private AdapterListPost adapterListPost;

    private int id;
    private int position;

    public FragmentPostOfCate() {
        postOfCates = new ArrayList<>();
    }

    // fun này tạo ra để hứng dữ liệu
    // ở đây có 2 param là id với position

    public static FragmentPostOfCate newInstance(int id, int position) {
        // Bundle dùng để đóng gói dữ liệu (d1: khởi tạo bundle)
        Bundle args = new Bundle();

        // đóng gói id vs position để chuyển
        // có 2 param, param1 là key, param2 là dữ liệu
        args.putInt(KEY_ID, id);
        args.putInt(KEY_POSITION, position);

        // Giờ mới khởi tạo fragment FragmentPostOfCate
        FragmentPostOfCate fragment = new FragmentPostOfCate();
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
        return inflater.inflate(R.layout.activity_post_of_cate_acitivity, container, false);
        // fun này giống setcontenview ở bên act có nhiệm vụ nhúng giao diện


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // sau đó chạy đến fun này
        initView(view);
        initData();
        postOfCates = new ArrayList<>();

        setupAdapter();

//        adapterListPost = new AdapterListPost(postOfCates, new ItemClickRv() {
//            @Override
//            public void onItemClick(int position, int id) {
//                // Khi gọi FragmentPostOfCate.newInstance() thì hàm newInstance sẽ chạy đầu tiên nhưng chưa khởi tạo fragment ở đây
//                ViewHelper.switchFragment(getActivity(), FragmentMediaOfPost.newInstanceMedia(id, position));
//            }
//        });
    }

    private void setupAdapter() {
        adapterListPost = new AdapterListPost(postOfCates, getActivity(), new ItemClickListener() {
            @Override
            public void onClick(int view, int position) {
                ViewHelper.switchFragment(getActivity(), FragmentMediaOfPost.newInstanceMedia(id, position));
            }
        });
                mRvListPost.setAdapter(adapterListPost);
        mRvListPost.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initData() {
        // ở đây mình sẽ kiểm tra xem có tín hiệu vận chuyển dữ liệu gì không?
        if (getArguments() != null) {
            // nếu có thì lấy ra id vs postion theo cái key
            id = getArguments().getInt(KEY_ID);
            position = getArguments().getInt(KEY_POSITION);
        }
        Hdwallpaper_Retrofit.getInstance().getPostOfCate(id, 5, 1).enqueue(new Callback<List<PostOfCate>>() {
            @Override
            public void onResponse(Call<List<PostOfCate>> call, Response<List<PostOfCate>> response) {
                if (response.body() != null) {
                    adapterListPost.updateData(response.body());
                    Log.e("listPost", "onResponse: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PostOfCate>> call, Throwable t) {
            }
        });
    }


    private void initView(View view) {
        mImgBack = view.findViewById(R.id.imgBack);
        mRvListPost = view.findViewById(R.id.rvListPost);
    }



    @Override
    public void onClick(int view, int position) {

    }
}
