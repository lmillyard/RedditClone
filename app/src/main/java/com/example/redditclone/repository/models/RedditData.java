package com.example.redditclone.repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RedditData {
    @JsonProperty("children")
    private List<RedditPostWrapper> children;

    public List<RedditPostWrapper> getChildren() {
        return children;
    }
}
