package com.slava.theapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registered{

    @SerializedName("#text")
    @Expose
    private Integer text;
    @SerializedName("unixtime")
    @Expose
    private String unixtime;

    public Integer getText() {
        return text;
    }

    public void setText(Integer text) {
        this.text = text;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }
}