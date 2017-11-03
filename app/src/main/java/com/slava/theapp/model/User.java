package com.slava.theapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("realname")
    @Expose
    private String realname;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("subscriber")
    @Expose
    private String subscriber;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("playlists")
    @Expose
    private String playlists;
    @SerializedName("bootstrap")
    @Expose
    private String bootstrap;
    @SerializedName("registered")
    @Expose
    private Registered registered;
    @SerializedName("type")
    @Expose
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String playlists) {
        this.playlists = playlists;
    }

    public String getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(String bootstrap) {
        this.bootstrap = bootstrap;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", realname='" + realname + '\'' +
                ", image=" + image +
                ", url='" + url + '\'' +
                ", country='" + country + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", subscriber='" + subscriber + '\'' +
                ", playcount='" + playcount + '\'' +
                ", playlists='" + playlists + '\'' +
                ", bootstrap='" + bootstrap + '\'' +
                ", registered=" + registered +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.realname);
        dest.writeList(this.image);
        dest.writeString(this.url);
        dest.writeString(this.country);
        dest.writeString(this.age);
        dest.writeString(this.gender);
        dest.writeString(this.subscriber);
        dest.writeString(this.playcount);
        dest.writeString(this.playlists);
        dest.writeString(this.bootstrap);
        dest.writeParcelable(this.registered, flags);
        dest.writeString(this.type);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.realname = in.readString();
        this.image = new ArrayList<Image>();
        in.readList(this.image, Image.class.getClassLoader());
        this.url = in.readString();
        this.country = in.readString();
        this.age = in.readString();
        this.gender = in.readString();
        this.subscriber = in.readString();
        this.playcount = in.readString();
        this.playlists = in.readString();
        this.bootstrap = in.readString();
        this.registered = in.readParcelable(Registered.class.getClassLoader());
        this.type = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
