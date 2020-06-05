package com.goncharoff.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.goncharoff.testapp.R;
import com.goncharoff.testapp.domain.Post;
import com.goncharoff.testapp.domain.json_objects.post.ActionType;
import com.goncharoff.testapp.domain.json_objects.post.PostAction;
import com.goncharoff.testapp.domain.json_objects.post.PostJson;
import com.goncharoff.testapp.domain.json_objects.post.PostQuote;
import com.goncharoff.testapp.repository.UserRepository;

import java.util.Date;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @Override
    public int getItemViewType(int position) {
        switch (posts.get(position).getPostType()) {
            case POST:
                return 1;
            case QUOTE:
                return 2;
            case ACTION:
                return 3;
            default:
                throw new IllegalArgumentException("Can not find post type");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
                return new PostItemViewHolder(view);
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.quote_item, parent, false);
                return new QuoteItemViewHolder(view);
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.promoting_item, parent, false);
                return new PromotingItemViewHolder(view);
            default:
                throw new IllegalArgumentException("Error during binding layout item");
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 1:
                PostItemViewHolder viewHolder = (PostItemViewHolder) holder;
                long postId = posts.get(position).getId();
                PostJson postJson = UserRepository.getINSTANCE().getPostJsonDataById(postId);
                viewHolder.bindView(posts.get(position).getDateCreated(), postJson.getImageUrl(), postJson.getText());
                break;
            case 2:
                QuoteItemViewHolder quoteItemViewHolder = (QuoteItemViewHolder) holder;
                long quoteId = posts.get(position).getId();
                PostQuote postQuote = UserRepository.getINSTANCE().getPostQuoteById(quoteId);
                quoteItemViewHolder.bindView(posts.get(position).getDateCreated(), postQuote.getFirstQuote(), postQuote.getSecondQuote());
                break;
            case 3:
                PromotingItemViewHolder promotingItemViewHolder = (PromotingItemViewHolder) holder;
                long promotingId = posts.get(position).getId();
                PostAction postAction = UserRepository.getINSTANCE().getPostActionById(promotingId);
                promotingItemViewHolder.bindView(postAction);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostItemViewHolder extends RecyclerView.ViewHolder {

        private TextView postDateCreated;
        private ImageView postImage;
        private TextView postMessage;

        public PostItemViewHolder(@NonNull View itemView) {
            super(itemView);

            postDateCreated = itemView.findViewById(R.id.postDate);
            postImage = itemView.findViewById(R.id.postImage);
            postMessage = itemView.findViewById(R.id.postMessage);

        }

        void bindView(long dateCreated, String imageUrl, String inputPostMessage) {
            postDateCreated.setText(new Date(dateCreated).toString());
            Glide.with(context).load(imageUrl).into(postImage);
            postMessage.setText(inputPostMessage);
        }
    }

    class PromotingItemViewHolder extends RecyclerView.ViewHolder {

        private TextView promotingMessage;
        private Button promotingButton;

        public PromotingItemViewHolder(@NonNull View itemView) {
            super(itemView);

            promotingMessage = itemView.findViewById(R.id.promotingMessage);
            promotingButton = itemView.findViewById(R.id.promotingButton);
        }

        void bindView(PostAction postAction) {
            promotingMessage.setText(postAction.getTitle());
            promotingButton.setText(postAction.getButtonName());
            if (postAction.getActionType() == ActionType.CALL) {
                promotingButton.setOnClickListener(it -> {
                    Log.d("ohmy", "its clicked");
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(UserRepository.getINSTANCE().getUserData().getPhone()));
                    context.startActivity(intent);
                });
            } else if (postAction.getActionType() == ActionType.EMAIL) {
                promotingButton.setOnClickListener(it -> {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "email@email.com", null));

                    context.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                });
            }
        }
    }

    class QuoteItemViewHolder extends RecyclerView.ViewHolder {

        private TextView dateCreatedTextView;
        private TextView firstQuoteTextView;
        private TextView secondQuoteTextView;

        public QuoteItemViewHolder(@NonNull View itemView) {
            super(itemView);
            dateCreatedTextView = itemView.findViewById(R.id.dateCreated);
            firstQuoteTextView = itemView.findViewById(R.id.firstQuote);
            secondQuoteTextView = itemView.findViewById(R.id.secondQuote);
        }

        void bindView(long dateCreated, String firstQuote, String secondQuote) {
            dateCreatedTextView.setText(new Date(dateCreated).toString());
            firstQuoteTextView.setText(firstQuote);
            secondQuoteTextView.setText(secondQuote);
        }
    }
}
