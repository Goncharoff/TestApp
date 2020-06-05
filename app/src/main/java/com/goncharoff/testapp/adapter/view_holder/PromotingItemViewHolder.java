package com.goncharoff.testapp.adapter.view_holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goncharoff.testapp.R;
import com.goncharoff.testapp.domain.json_objects.post.ActionType;
import com.goncharoff.testapp.domain.json_objects.post.PostAction;

public class PromotingItemViewHolder extends RecyclerView.ViewHolder {

    private TextView promotingMessage;
    private Button promotingButton;
    private Context context;

    public PromotingItemViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        promotingMessage = itemView.findViewById(R.id.promotingMessage);
        promotingButton = itemView.findViewById(R.id.promotingButton);
    }

    public void bindView(PostAction postAction) {
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
