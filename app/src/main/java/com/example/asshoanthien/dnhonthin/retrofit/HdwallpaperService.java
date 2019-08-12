package com.example.asshoanthien.dnhonthin.retrofit;

import com.example.asshoanthien.dnhonthin.model.modelpostofcate.PostOfCate;
import com.example.asshoanthien.dnhonthin.model.modelLatest.Latestt;
import com.example.asshoanthien.dnhonthin.model.model_cate.Category;
import com.example.asshoanthien.dnhonthin.model.modelmediaofpost.MediaOfPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HdwallpaperService {
    //http://asian.dotplays.com/wp-json/wp/v2/posts?_embed
   // http://asian.dotplays.com/wp-json/wp/v2/media?parent=377
    @GET("wp-json/wp/v2/media")
    Call<List<Latestt>> getSourceUrl();

    @GET("wp-json/wp/v2/categories")
    Call<List<Category>> getCategpries(@Query("page") int page,
                                      @Query("per_page") int per_page);


    //http://asian.dotplays.com/wp-json/wp/v2/posts?categories=26&per_page=5&paging=1
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostOfCate>> getPostOfCate(@Query("categories") int categories,
                                         @Query("per_page") int per_page,
                                         @Query("paging") int paging);
    //    http://asian.dotplays.com/wp-json/wp/v2/media?parent=524
    @GET("wp-json/wp/v2/media/")
    Call<List<MediaOfPost>> getMediaOfPost(@Query("parent") int id);
}
