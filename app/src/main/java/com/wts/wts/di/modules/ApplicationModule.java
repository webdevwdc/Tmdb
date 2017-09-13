package com.wts.wts.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.wts.wts.app.TrackApplication;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private TrackApplication mContext;

    public ApplicationModule(TrackApplication mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    Context getContextApp(){
        return mContext;
    }

    @Provides
    @Singleton
    SharedPreferences getMySharedPRefObj()
    {
        return mContext.getSharedPreferences("APP_DATA",Context.MODE_PRIVATE);
    }
}
