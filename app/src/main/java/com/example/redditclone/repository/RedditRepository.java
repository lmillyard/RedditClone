package com.example.redditclone.repository;

import com.example.redditclone.callbacks.GeneralCallback;
import com.example.redditclone.repository.models.HomePageResponse;
import com.example.redditclone.repository.services.RedditService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RedditRepository {
    private static RedditRepository instance;

    private Retrofit retrofit;

    private  RedditRepository() {
        ObjectMapper objectMapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

    public static RedditRepository getInstance() {
        if (instance == null){
            instance = new RedditRepository();
        }
        return instance;
    }

    public void getHomePage(GeneralCallback<HomePageResponse> callback) {
        RedditService service = retrofit.create(RedditService.class);
        Call<HomePageResponse> call = service.getHomePage();
        call.enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure((response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<HomePageResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }
}
