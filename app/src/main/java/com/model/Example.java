
package com.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;


public class Example {
    @Expose
    private Photos photos;
    @Expose
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}