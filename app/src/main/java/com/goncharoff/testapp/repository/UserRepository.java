package com.goncharoff.testapp.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.RawRes;

import com.goncharoff.testapp.R;
import com.goncharoff.testapp.domain.User;
import com.goncharoff.testapp.domain.post.PostData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class UserRepository {

    private User userData;
    private PostData postData;
    private static UserRepository INSTANCE = null;
    private Context context;

    public UserRepository(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(context);
        }
    }

    public static UserRepository getINSTANCE() {
        if (INSTANCE == null) throw new IllegalStateException("User must not be null");

        return INSTANCE;
    }


    public User getUserData() {
        if (userData == null) {
            userData = parseJsonFromFile(context, R.raw.user, User.class);
        }
        Log.d("repo", "getting data" + parseJsonFromFile(context, R.raw.user, User.class));

        return userData;
    }

    public PostData getPostData() {
        if (postData == null) {
            postData = parseJsonFromFile(context, R.raw.data, PostData.class);
        }
        return postData;
    }

    private <T> T parseJsonFromFile(Context context, @RawRes int id, Class<T> resultClass) {
        return new Gson().fromJson(new BufferedReader(new InputStreamReader(context.getResources().openRawResource(id), StandardCharsets.UTF_8)), resultClass);
    }
}
