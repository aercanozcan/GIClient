package com.apptopus.gettyimageclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ercanozcan on 09/08/17.
 */

public class GettyResponse<T> {

    @SerializedName("result_count")
    private int result_count;

    @SerializedName("images")
    private List<T> images;

    public int getResult_count() {
        return result_count;
    }

    public void setResult_count(int result_count) {
        this.result_count = result_count;
    }

    public List<T> getImages() {
        return images;
    }

    public void setImages(List<T> images) {
        this.images = images;
    }
}
