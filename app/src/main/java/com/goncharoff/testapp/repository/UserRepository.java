package com.goncharoff.testapp.repository;

import android.content.Context;

import androidx.annotation.RawRes;

import com.goncharoff.testapp.R;
import com.goncharoff.testapp.domain.User;
import com.goncharoff.testapp.domain.post.PostData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class UserRepository {

    private static User userData;
    private static PostData postData;

    private UserRepository() {
    }

    public static User getUserData(Context context) {
        if (userData == null) {
            userData = parseJsonFromFile(context, R.raw.user, User.class);
        }
        return userData;
    }

    public static PostData getPostData(Context context) {
        if (postData == null) {
            postData = parseJsonFromFile(context, R.raw.data, PostData.class);
        }
        return postData;
    }

    private static <T> T parseJsonFromFile(Context context, @RawRes long id, Class<T> resultClass) {
        return new Gson().fromJson(new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.data), StandardCharsets.UTF_8)), resultClass);
    }
}
