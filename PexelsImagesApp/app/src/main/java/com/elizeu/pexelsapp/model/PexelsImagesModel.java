package com.elizeu.pexelsapp.model;

import com.google.gson.annotations.SerializedName;

public class PexelsImagesModel {
    @SerializedName("photographer")
    String Photographer;
    @SerializedName("alt")
    String Description;
    @SerializedName("url")
    String ImageFileName;


    @SerializedName("src")
    PexelsImageSourcesModel src;

    public String getPhotographer() {
        return Photographer;
    }

    public String getDescription() {
        return Description;
    }

    public String getImageFileName() {
        return ImageFileName;
    }

    public PexelsImageSourcesModel getSrc() {
        return src;
    }
    public PexelsImagesModel(String photographer, String description, String imageFileName) {
        Photographer = photographer;
        Description = description;
        ImageFileName = imageFileName;
    }
}
