package com.example.redditclone.repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HomePageResponse {
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("data")
    private RedditData data;

    public String getKind() {
        return kind;
    }

    public List<RedditPostWrapper> getData() {
        return data.getChildren();
    }
}
