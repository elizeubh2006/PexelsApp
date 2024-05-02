package com.elizeu.pexelsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PexelsSearchResultModel {
    public PexelsSearchResultModel(List<PexelsImagesModel> photos) {
        this.photos = photos;
    }

    @SerializedName("photos")
    List<PexelsImagesModel> photos;

    public List<PexelsImagesModel> getPhotos() {
        return photos;
    }
}
