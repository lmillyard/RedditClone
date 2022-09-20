package com.example.redditclone.repository.services;

import com.example.redditclone.repository.models.HomePageResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RedditService {
    @GET(".json")
    Call<HomePageResponse> getHomePage();
}
