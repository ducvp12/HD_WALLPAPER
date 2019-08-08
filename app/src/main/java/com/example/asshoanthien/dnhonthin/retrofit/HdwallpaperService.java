package com.example.asshoanthien.dnhonthin.retrofit;

import com.example.asshoanthien.dnhonthin.model.modelLatest.Latestt;
import com.example.asshoanthien.dnhonthin.model.model_cate.Category;

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

}
