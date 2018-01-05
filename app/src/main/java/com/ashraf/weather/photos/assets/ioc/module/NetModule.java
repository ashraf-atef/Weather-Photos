package com.ashraf.weather.photos.assets.ioc.module;

import com.ashraf.weather.photos.assets.constant.Constant;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ashraf.weather.photos.datalayer.remote.api.ApiMethods;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    String mApiUrl;

    public NetModule(String apiUrl) {
        this.mApiUrl = apiUrl;
    }

    @Provides
    @Singleton
    Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("appid", Constant.APP_ID)
                        .build();
                return chain.proceed(request);
            }
        };

    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Interceptor headerInterceptor) {
        return new OkHttpClient
                .Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mApiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiMethods providesApiMethods(Retrofit retrofit) {
        return retrofit.create(ApiMethods.class);
    }
}
