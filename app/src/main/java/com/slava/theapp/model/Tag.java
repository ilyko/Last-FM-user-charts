package com.slava.theapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Tag {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("reach")
        @Expose
        private String reach;
        @SerializedName("taggings")
        @Expose
        private String taggings;
        @SerializedName("streamable")
        @Expose
        private String streamable;
        @SerializedName("wiki")
        @Expose
        private Wiki wiki;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getReach() {
            return reach;
        }

        public void setReach(String reach) {
            this.reach = reach;
        }

        public String getTaggings() {
            return taggings;
        }

        public void setTaggings(String taggings) {
            this.taggings = taggings;
        }

        public String getStreamable() {
            return streamable;
        }

        public void setStreamable(String streamable) {
            this.streamable = streamable;
        }

        public Wiki getWiki() {
            return wiki;
        }

        public void setWiki(Wiki wiki) {
            this.wiki = wiki;
        }

        @Override
        public String toString() {
            return "Tag{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    ", reach='" + reach + '\'' +
                    ", taggings='" + taggings + '\'' +
                    ", streamable='" + streamable + '\'' +
                    ", wiki=" + wiki +
                    '}';
        }
    }
