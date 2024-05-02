package com.elizeu.pexelsapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface PexelsImagesApi {
    @GET("/v1/search?per_page=40&query=people")
    Single<PexelsSearchResultModel> getPxImages();


}
