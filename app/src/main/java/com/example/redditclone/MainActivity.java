package com.example.redditclone;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditclone.callbacks.GeneralCallback;
import com.example.redditclone.repository.RedditRepository;
import com.example.redditclone.repository.models.HomePageResponse;
import com.example.redditclone.repository.models.RedditPost;
import com.example.redditclone.repository.models.RedditPostWrapper;
import com.example.redditclone.ui.PostsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GeneralCallback<HomePageResponse> {

    RecyclerView homePageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePageRecyclerView = findViewById(R.id.homePageRecyclerView);
        homePageRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RedditRepository repository = RedditRepository.getInstance();
        repository.getHomePage(this);

    }

    private void updateUI(List<RedditPostWrapper> data) {
        List<RedditPost> list = new ArrayList<>();
        for (RedditPostWrapper wrapper : data) {
            list.add(wrapper.getData());
        }
        homePageRecyclerView.setAdapter(new PostsAdapter(this, list));
    }

    @Override
    public void onSuccess(HomePageResponse homePageResponse) {
        updateUI(homePageResponse.getData());
    }


    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}