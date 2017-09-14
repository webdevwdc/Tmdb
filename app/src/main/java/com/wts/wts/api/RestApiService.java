package com.wts.wts.api;

import com.wts.wts.modules.signup.model.SignUpRequest;
import com.wts.wts.mvp.model.home.MoviesDateRequest;
import com.wts.wts.mvp.model.home.MoviesResults;
import com.wts.wts.mvp.model.login.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by android on 12/9/17.
 *
 */

public interface RestApiService {

    @FormUrlEncoded
    @POST("login")
    Observable<LoginRequest> login(@Field("email") String email,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("registerUser")
    Observable<SignUpRequest> registerUser(@Field("firstName") String firstName,
                                     @Field("lastName") String lastName,
                                     @Field("password") String password,
                                     @Field("email") String email,
                                     @Field("contactNo") String contactNo);

    @GET("?api_key=da86572d94091de3671c20ed449c4c70&language=en&page=1&primary_release_date.gte=2017-07-01&primary_release_date.lte=2017-08-28")
    Observable<MoviesDateRequest> getMoviesByDate();

    @GET("?api_key=da86572d94091de3671c20ed449c4c70&movie/popular")
    Observable<MoviesDateRequest> getMoviesByPopularity();

    @GET("?api_key=da86572d94091de3671c20ed449c4c70&language=en&page=1&vote_count.gte=500&year=2017")
    Observable<MoviesDateRequest> getMoviesByTopRated();
}
