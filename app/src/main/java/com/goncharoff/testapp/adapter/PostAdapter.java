package com.goncharoff.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goncharoff.testapp.R;
import com.goncharoff.testapp.adapter.view_holder.PostItemViewHolder;
import com.goncharoff.testapp.adapter.view_holder.PromotingItemViewHolder;
import com.goncharoff.testapp.adapter.view_holder.QuoteItemViewHolder;
import com.goncharoff.testapp.domain.Post;
import com.goncharoff.testapp.domain.json_objects.post.PostAction;
import com.goncharoff.testapp.domain.json_objects.post.PostJson;
import com.goncharoff.testapp.domain.json_objects.post.PostQuote;
import com.goncharoff.testapp.repository.UserRepository;

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
                return new PostItemViewHolder(view, context);
            case QUOTE_CODE:
                view = inflateLayout(R.layout.quote_item, parent);
                return new QuoteItemViewHolder(view);
            case ACTION_CODE:
                view = inflateLayout(R.layout.promoting_item, parent);
                return new PromotingItemViewHolder(view, context);
            default:
                throw new IllegalArgumentException("Error during binding layout item for code " + viewType);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        long postId = posts.get(position).getId();

        switch (holder.getItemViewType()) {
            case POST_CODE:
                PostJson postJson = userRepository.providePostJsonDataById(postId);
                ((PostItemViewHolder) holder).bindView(posts.get(position).getDateCreated(), postJson);
                break;
            case QUOTE_CODE:
                PostQuote postQuote = userRepository.providePostQuoteById(postId);
                ((QuoteItemViewHolder) holder).bindView(posts.get(position).getDateCreated(), postQuote.getFirstQuote(), postQuote.getSecondQuote());
                break;
            case ACTION_CODE:
                PostAction postAction = userRepository.providePostActionById(postId);
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

}
