package com.goncharoff.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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

    private final static int POST_CODE = 1;
    private final static int QUOTE_CODE = 2;
    private final static int ACTION_CODE = 3;

    private Context context;
    private List<Post> posts;
    private UserRepository userRepository = UserRepository.getINSTANCE();

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @Override
    public int getItemViewType(int position) {
        switch (posts.get(position).getPostType()) {
            case POST:
                return POST_CODE;
            case QUOTE:
                return QUOTE_CODE;
            case ACTION:
                return ACTION_CODE;
            default:
                throw new IllegalArgumentException("Can not find post type with action = " + posts.get(position).getPostType());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case POST_CODE:
                view = inflateLayout(R.layout.post_item, parent);
                return new PostItemViewHolder(view);
            case QUOTE_CODE:
                view = inflateLayout(R.layout.quote_item, parent);
                return new QuoteItemViewHolder(view);
            case ACTION_CODE:
                view = inflateLayout(R.layout.promoting_item, parent);
                return new PromotingItemViewHolder(view);
            default:
                throw new IllegalArgumentException("Error during binding layout item for code " + viewType);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case POST_CODE:
                PostJson postJson = userRepository.getPostJsonDataById(posts.get(position).getId());
                ((PostItemViewHolder) holder).bindView(posts.get(position).getDateCreated(), postJson);
                break;
            case QUOTE_CODE:
                PostQuote postQuote = userRepository.getPostQuoteById(posts.get(position).getId());
                ((QuoteItemViewHolder) holder).bindView(posts.get(position).getDateCreated(), postQuote.getFirstQuote(), postQuote.getSecondQuote());
                break;
            case ACTION_CODE:
                PostAction postAction = userRepository.getPostActionById(posts.get(position).getId());
                ((PromotingItemViewHolder) holder).bindView(postAction);
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

        void bindView(long dateCreated, PostJson postJson) {
            postDateCreated.setText(DateFormat.format("hh:mm, dd MMM yyyy", new Date(dateCreated)));
            Glide.with(context).load(postJson.getImageUrl()).into(postImage);
            postMessage.setText(postJson.getText());
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
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + postAction.getTarget()));
                    context.startActivity(intent);
                });
            } else if (postAction.getActionType() == ActionType.EMAIL) {
                promotingButton.setOnClickListener(it -> {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", postAction.getTarget(), null));

                    context.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                });
            }
        }
    }

    class QuoteItemViewHolder extends RecyclerView.ViewHolder {

        private TextView dateCreatedTextView;
        private TextView firstQuoteTextView;
        private TextView secondQuoteTextView;
        private TextView thirdQuote;
        private FrameLayout firstQuoteHolder;
        private FrameLayout secondQuoteHolder;
        private FrameLayout thirdQuoteHolder;

        public QuoteItemViewHolder(@NonNull View itemView) {
            super(itemView);
            dateCreatedTextView = itemView.findViewById(R.id.dateCreated);
            firstQuoteTextView = itemView.findViewById(R.id.firstQuote);
            secondQuoteTextView = itemView.findViewById(R.id.secondQuote);
            firstQuoteHolder = itemView.findViewById(R.id.firstQuoteHolder);
            secondQuoteHolder = itemView.findViewById(R.id.secondQuoteHolder);
            thirdQuoteHolder = itemView.findViewById(R.id.thirdQuoteHolder);
            thirdQuote = itemView.findViewById(R.id.thirdQuote);
        }

        void bindView(long dateCreated, String firstQuote, String secondQuote) {
            dateCreatedTextView.setText(DateFormat.format("hh:mm, dd MMM yyyy", new Date(dateCreated)));

            if (firstQuote == null || firstQuote.equals("")) {
                firstQuoteHolder.setVisibility(View.INVISIBLE);
                secondQuoteHolder.setVisibility(View.INVISIBLE);
                thirdQuoteHolder.setVisibility(View.VISIBLE);
                thirdQuote.setText(secondQuote);
            } else if (secondQuote == null || secondQuote.equals("")) {
                firstQuoteHolder.setVisibility(View.INVISIBLE);
                secondQuoteHolder.setVisibility(View.INVISIBLE);
                thirdQuoteHolder.setVisibility(View.VISIBLE);
                thirdQuote.setText(secondQuote);
            } else {
                firstQuoteTextView.setText(firstQuote);
                secondQuoteTextView.setText(secondQuote);
            }
        }
    }
}
