package com.goncharoff.testapp.adapter.view_holder;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.goncharoff.testapp.R;
import com.goncharoff.testapp.domain.json_objects.post.PostJson;

import java.util.Date;

import static com.goncharoff.testapp.adapter.PostAdapter.DATE_FORMAT_PATTERN;

public class PostItemViewHolder extends RecyclerView.ViewHolder {

    private TextView postDateCreated;
    private ImageView postImage;
    private TextView postMessage;
    private Context context;

    public PostItemViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        this.context = context;
        postDateCreated = itemView.findViewById(R.id.postDate);
        postImage = itemView.findViewById(R.id.postImage);
        postMessage = itemView.findViewById(R.id.postMessage);

    }

    public void bindView(long dateCreated, PostJson postJson) {
        postDateCreated.setText(DateFormat.format(DATE_FORMAT_PATTERN, new Date(dateCreated)));
        Glide.with(context).load(postJson.getImageUrl()).into(postImage);
        postMessage.setText(postJson.getText());
    }
}
