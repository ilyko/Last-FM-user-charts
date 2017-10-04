package com.slava.theapp.model.chart;

/**
 * Created by slava on 03.10.17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.slava.theapp.model.Tags;

public class TopTags {

    @SerializedName("tags")
    @Expose
    private Tags tags;

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "TopTags{" +
                "tags=" + tags +
                '}';
    }
}
