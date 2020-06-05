package com.goncharoff.testapp.adapter.view_holder;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goncharoff.testapp.R;

import java.util.Date;

import static com.goncharoff.testapp.adapter.PostAdapter.DATE_FORMAT_PATTERN;

public class QuoteItemViewHolder extends RecyclerView.ViewHolder {

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

    public void bindView(long dateCreated, String firstQuote, String secondQuote) {

        dateCreatedTextView.setText(DateFormat.format(DATE_FORMAT_PATTERN, new Date(dateCreated)));

        /** if one of quotes is "" or null - make the two of them invisible and make third one visible with
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
