package com.slava.theapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("tag")
    @Expose
    private List<Tag> tag = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "tag=" + tag +
                ", attr=" + attr +
                '}';
    }
}
