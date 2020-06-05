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

import androidx.annotation.LayoutRes;
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
    public static final String DATE_FORMAT_PATTERN = "hh:mm, dd MMM yyyy";

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
        long postId = posts.get(position).getId();

        switch (holder.getItemViewType()) {
            case POST_CODE:
                PostJson postJson = userRepository.getPostJsonDataById(postId);
                ((PostItemViewHolder) holder).bindView(posts.get(position).getDateCreated(), postJson);
                break;
            case QUOTE_CODE:
                PostQuote postQuote = userRepository.getPostQuoteById(postId);
                ((QuoteItemViewHolder) holder).bindView(posts.get(position).getDateCreated(), postQuote.getFirstQuote(), postQuote.getSecondQuote());
                break;
            case ACTION_CODE:
                PostAction postAction = userRepository.getPostActionById(postId);
                ((PromotingItemViewHolder) holder).bindView(postAction);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    private View inflateLayout(@LayoutRes int layoutRes, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutRes, parent, false);
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
            postDateCreated.setText(DateFormat.format(DATE_FORMAT_PATTERN, new Date(dateCreated)));
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
            setUpButtOnClickListnerByActionType(postAction.getActionType(), postAction.getTarget());
        }

        private void setUpButtOnClickListnerByActionType(ActionType actionType, String target) {
            if (actionType == ActionType.CALL) {
                promotingButton.setOnClickListener(it -> bindPromotingButtonToCall(target));
            } else if (actionType == ActionType.EMAIL) {
                promotingButton.setOnClickListener(it -> bindPromotingButtonToEmail(target));
            }
        }

        private void bindPromotingButtonToCall(String target) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", target, null));
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.app_to_call)));
        }

        private void bindPromotingButtonToEmail(String target) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", target, null));

            context.startActivity(Intent.createChooser(intent, context.getString(R.string.app_to_email)));
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

            dateCreatedTextView.setText(DateFormat.format(DATE_FORMAT_PATTERN, new Date(dateCreated)));

            /**if one of qoutes is "" or null - make the two of them invisible and make third one visible with
             text of quote which is present;
             **/
            if (firstQuote == null || firstQuote.equals("")) {
                switchQuotesViews(secondQuote);
            } else if (secondQuote == null || secondQuote.equals("")) {
                switchQuotesViews(firstQuote);
            } else {
                firstQuoteTextView.setText(firstQuote);
                secondQuoteTextView.setText(secondQuote);
            }
        }

        private void switchQuotesViews(String quoteText) {
            firstQuoteHolder.setVisibility(View.INVISIBLE);
            secondQuoteHolder.setVisibility(View.INVISIBLE);
            thirdQuoteHolder.setVisibility(View.VISIBLE);
            thirdQuote.setText(quoteText);
        }
    }


}
