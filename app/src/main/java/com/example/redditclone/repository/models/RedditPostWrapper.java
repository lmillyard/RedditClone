package com.example.redditclone.repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedditPostWrapper {
    @JsonProperty("data")
    private RedditPost data;

    public RedditPost getData() {
        return data;
    }
}
