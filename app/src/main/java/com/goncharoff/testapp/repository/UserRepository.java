package com.goncharoff.testapp.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import com.goncharoff.testapp.R;
import com.goncharoff.testapp.domain.Post;
import com.goncharoff.testapp.domain.json_objects.User;
import com.goncharoff.testapp.domain.json_objects.post.Dateable;
import com.goncharoff.testapp.domain.json_objects.post.PostAction;
import com.goncharoff.testapp.domain.json_objects.post.PostData;
import com.goncharoff.testapp.domain.json_objects.post.PostJson;
import com.goncharoff.testapp.domain.json_objects.post.PostQuote;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class UserRepository {

    private User userData;
    private PostData postData;
    private static UserRepository INSTANCE = null;
    private Context context;

    private UserRepository(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(context);
        }
    }

    public static UserRepository getINSTANCE() {
        if (INSTANCE == null) throw new IllegalStateException("Repository must not be null");

        return INSTANCE;
    }


    public User getUserData() {
        if (userData == null) {
            userData = parseJsonFromFile(context, R.raw.user, User.class);
        }

        return userData;
    }

    public PostData getPostData() {
        if (postData == null) {
            postData = parseJsonFromFile(context, R.raw.data, PostData.class);
        }
        return postData;
    }

    public List<Post> getFilteredAndOrderedPosts() {
        PostData postData = getPostData();
        List<Post> input = Post.fromData(postData, postData.getShowFromDate());
        Collections.sort(input);

        return input;
    }

    public PostJson providePostJsonDataById(long id) {
        return getDateableDataById(id, getPostData().getPosts());
    }

    public PostAction providePostActionById(long id) {
        return getDateableDataById(id, getPostData().getPostActions());
    }


    public PostQuote providePostQuoteById(long id) {
        return getDateableDataById(id, getPostData().getPostQuotes());
    }

    @Nullable
    private <T extends Dateable> T getDateableDataById(long id, List<T> collectionToSearch) {
        for (int i = 0; i < collectionToSearch.size(); i++) {
            if (collectionToSearch.get(i).getId() == id) {
                return collectionToSearch.get(i);
            }
        }

        return null;
    }

    private <T> T parseJsonFromFile(Context context, @RawRes int id, Class<T> resultClass) {
        return new Gson().fromJson(new BufferedReader(new InputStreamReader(context.getResources().openRawResource(id), StandardCharsets.UTF_8)), resultClass);
    }

}
