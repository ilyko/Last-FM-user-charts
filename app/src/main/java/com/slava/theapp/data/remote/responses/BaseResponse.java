package com.slava.theapp.data.remote.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by slava on 30.10.17.
 */

public abstract class BaseResponse {
    @Override
    public String toString() {
        return "BaseResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", links=" + links +
                '}';
    }

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("links")
    @Expose
    private List<Object> links = null;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getLinks() {
        return links;
    }

    public void setLinks(List<Object> links) {
        this.links = links;
    }
}
