package com.example.redditclone.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.redditclone.R;
import com.example.redditclone.repository.models.RedditPost;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_VIDEO = 0;
    private static final int VIEW_TYPE_OTHER = 1;
    private Context context;
    private List<RedditPost> redditPosts;

    public PostsAdapter(Context context, List<RedditPost> redditPosts) {
        this.context = context;
        this.redditPosts = redditPosts;
    }

    @Override
    public int getItemViewType(int position) {
        RedditPost redditPost = redditPosts.get(position);
        if (redditPost.isVideo()) {
            return VIEW_TYPE_VIDEO;
        }
        return VIEW_TYPE_OTHER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_VIDEO:
                return new PostVideoViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.reddit_video_post_layout, parent, false));
            case VIEW_TYPE_OTHER:
                return new PostOtherViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.reddit_other_post_layout, parent, false));
            default:
                throw new IllegalArgumentException("Unsupported view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RedditPost redditPost = redditPosts.get(position);
        if (holder instanceof PostOtherViewHolder) {
            bindOtherPost((PostOtherViewHolder) holder, redditPost);
        } else {
            bindVideoPost((PostVideoViewHolder) holder, redditPost);
        }
    }

    private void bindVideoPost(PostVideoViewHolder holder, RedditPost redditPost) {
        holder.titleTextView.setText(redditPost.getTitle());
        holder.authorTextView.setText(redditPost.getAuthor());
        Glide.with(context)
                .load(redditPost.getThumbnailUrl()).into(holder.thumbnailImageView);
        holder.containerLayout.setOnClickListener(v -> openDetails(redditPost));
    }

    private void bindOtherPost(PostOtherViewHolder holder, RedditPost redditPost) {
        holder.titleTextView.setText(redditPost.getTitle());
        holder.authorTextView.setText(redditPost.getAuthor());
        holder.upVotesTextView.setText(String.valueOf(redditPost.getUpVotes()));
        holder.downVotesTextView.setText(String.valueOf(redditPost.getDownVotes()));
        holder.containerLayout.setOnClickListener(v -> openDetails(redditPost));

    }

    private void openDetails(RedditPost redditPost) {
    }

    @Override
    public int getItemCount() {
        return redditPosts.size();
    }

    static class PostOtherViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView, upVotesTextView, downVotesTextView;
        View containerLayout;

        public PostOtherViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            upVotesTextView = itemView.findViewById(R.id.upVotesTextView);
            downVotesTextView = itemView.findViewById(R.id.downVotesTextView);
            containerLayout = itemView.findViewById(R.id.containerLayout);
        }
    }

    static class PostVideoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView;
        ImageView thumbnailImageView;
        View containerLayout;

        public PostVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            containerLayout = itemView.findViewById(R.id.containerLayout);

        }
    }
}
