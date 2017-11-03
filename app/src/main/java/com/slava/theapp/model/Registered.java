package com.slava.theapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registered implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.text);
        dest.writeString(this.unixtime);
    }

    public Registered() {
    }

    protected Registered(Parcel in) {
        this.text = (Integer) in.readValue(Integer.class.getClassLoader());
        this.unixtime = in.readString();
    }

    public static final Parcelable.Creator<Registered> CREATOR = new Parcelable.Creator<Registered>() {
        @Override
        public Registered createFromParcel(Parcel source) {
            return new Registered(source);
        }

        @Override
        public Registered[] newArray(int size) {
            return new Registered[size];
        }
    };
}