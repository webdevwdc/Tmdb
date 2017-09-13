package com.wts.wts.di.modules;

import com.wts.wts.api.RestApiService;
import com.wts.wts.di.scope.PerActivity;
import com.wts.wts.mvp.view.SignUpView;
import com.wts.wts.utils.Constants;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 13/9/17.
 */

@Module
public class SignUpModule {

    SignUpView signupView;

    public SignUpModule(SignUpView signupView) {
        this.signupView = signupView;
    }

    @PerActivity
    @Provides
    SignUpView provideView() {
        return signupView;
    }

    @PerActivity
    @Provides
    RestApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(RestApiService.class);
    }

    @PerActivity
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @PerActivity
    @Provides
    OkHttpClient provideOkHttpClient2() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @PerActivity
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @PerActivity
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory converterFactory,
                             RxJavaCallAdapterFactory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }
}
