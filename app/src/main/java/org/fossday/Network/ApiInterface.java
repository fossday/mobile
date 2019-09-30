package org.fossday.Network;

import org.fossday.Model.Category;
import org.fossday.Model.Media;
import org.fossday.Model.SponsorSupporter;
import org.fossday.Model.Talk;
import org.fossday.Model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("categories")
    Call<List<Category>> getCategories (
            @Query("per_page") int quantity
    );

    @GET("talks")
    Call<List<Talk>> getLectures (
            @Query("per_page") int quantity
    );

    @GET("users")
    Call<List<User>> getSpeakers(
            @Query("per_page") int quantity
    );

    @GET("sponsors")
    Call<List<SponsorSupporter>> getSponsors (
            @Query("per_page") int quantity
    );

    @GET("supporter")
    Call<List<SponsorSupporter>> getSupporters (
            @Query("per_page") int quantity
    );

    @GET
    Call<Media> getMedias (
            @Url String url
    );

    @GET
    Call<ResponseBody> download (
            @Url String fileUrl
    );

}
