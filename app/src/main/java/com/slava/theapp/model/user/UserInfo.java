package com.slava.theapp.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.slava.theapp.data.remote.responses.BaseResponse;
import com.slava.theapp.model.User;

public class UserInfo extends BaseResponse{

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return super.toString()+" UserInfo{" +
                "user=" + user +
                '}';
    }
}