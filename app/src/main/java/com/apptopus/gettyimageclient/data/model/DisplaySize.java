package com.apptopus.gettyimageclient.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ercanozcan on 09/08/17.
 */

public class DisplaySize {
    @SerializedName("is_watermarked")
    private boolean watermarked;
    @SerializedName("name")
    private String name;
    @SerializedName("uri")
    private String uri;

    public boolean isWatermarked() {
        return watermarked;
    }

    public void setWatermarked(boolean watermarked) {
        this.watermarked = watermarked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
