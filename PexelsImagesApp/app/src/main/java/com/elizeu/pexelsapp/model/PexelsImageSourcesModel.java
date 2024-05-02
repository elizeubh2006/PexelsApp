package com.elizeu.pexelsapp.model;

import com.google.gson.annotations.SerializedName;

public class PexelsImageSourcesModel {

    @SerializedName("original")
    String original;
    @SerializedName("large2x")
    String large2x;
    @SerializedName("large")
    String large;
    @SerializedName("medium")
    String medium;
    @SerializedName("small")
    String small;
    @SerializedName("portrait")
    String portrait;
    @SerializedName("landscape")
    String landscape;
    @SerializedName("tiny")
    String tiny;

    public String getOriginal() {
        return original;
    }

    public String getLarge2x() {
        return large2x;
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getSmall() {
        return small;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getLandscape() {
        return landscape;
    }

    public String getTiny() {
        return tiny;
    }

    public PexelsImageSourcesModel(String original, String large2x, String large, String medium, String small, String portrait, String landscape, String tiny) {
        this.original = original;
        this.large2x = large2x;
        this.large = large;
        this.medium = medium;
        this.small = small;
        this.portrait = portrait;
        this.landscape = landscape;
        this.tiny = tiny;
    }
}
