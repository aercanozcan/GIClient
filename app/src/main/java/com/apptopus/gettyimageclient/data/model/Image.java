package com.apptopus.gettyimageclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ercanozcan on 09/08/17.
 */

public class Image {
    @SerializedName("id")
    private String id;
    @SerializedName("asset_family")
    private String assetFamily;
    @SerializedName("caption")
    private String caption;
    @SerializedName("collection_code")
    private String collectionCode;
    @SerializedName("collection_name")
    private String collectionName;
    @SerializedName("title")
    private String title;
    @SerializedName("display_sizes")
    private List<DisplaySize> displaySizes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetFamily() {
        return assetFamily;
    }

    public void setAssetFamily(String assetFamily) {
        this.assetFamily = assetFamily;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCollectionCode() {
        return collectionCode;
    }

    public void setCollectionCode(String collectionCode) {
        this.collectionCode = collectionCode;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DisplaySize> getDisplaySizes() {
        return displaySizes;
    }

    public void setDisplaySizes(List<DisplaySize> displaySizes) {
        this.displaySizes = displaySizes;
    }
}
