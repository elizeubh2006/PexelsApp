package com.elizeu.pexelsapp.model;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PexelsService {
    private static final String BASE_URL = "https://api.pexels.com";
    private static final String TOKEN = "hKSgpoQd6hWthnLq8v8X49eOSpQUehXC3wmyWjna42X61MoZjUWxo7Vx";
    private static PexelsService instance;
    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", TOKEN)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();
    private PexelsImagesApi api = new Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PexelsImagesApi.class);
    private PexelsService(){

    }

    public static PexelsService getInstance(){
        if(instance == null){
            instance = new PexelsService();
        }
        return instance;
    }

    public Single<PexelsSearchResultModel> getPxImages() {
        return  api.getPxImages();
    }
}
