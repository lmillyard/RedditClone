package com.example.redditclone.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditclone.R;
import com.example.redditclone.repository.models.RedditPost;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private Context context;
    private List<RedditPost> redditPosts;

    public PostsAdapter(Context context, List<RedditPost> redditPosts) {
        this.context = context;
        this.redditPosts = redditPosts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(context).inflate(R.layout.reddit_post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        RedditPost redditPost = redditPosts.get(position);
        bind(holder, redditPost);
    }

    private void bind(PostViewHolder holder, RedditPost redditPost) {
        holder.titleTextView.setText(redditPost.getTitle());
        holder.authorTextView.setText(redditPost.getAuthor());
        holder.upVotesTextView.setText(String.valueOf(redditPost.getUpVotes()));
        holder.downVotesTextView.setText(String.valueOf(redditPost.getDownVotes()));
    }

    @Override
    public int getItemCount() {
        return redditPosts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView, upVotesTextView, downVotesTextView;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            upVotesTextView = itemView.findViewById(R.id.upVotesTextView);
            downVotesTextView = itemView.findViewById(R.id.downVotesTextView);
        }
    }
}
