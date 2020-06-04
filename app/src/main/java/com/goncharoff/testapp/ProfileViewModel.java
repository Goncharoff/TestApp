package com.goncharoff.testapp;

import androidx.lifecycle.ViewModel;

import com.goncharoff.testapp.domain.User;
import com.goncharoff.testapp.domain.post.PostData;
import com.goncharoff.testapp.repository.UserRepository;

public class ProfileViewModel extends ViewModel {

    private User userData;
    private PostData postData;

    public ProfileViewModel() {
        userData = UserRepository.getINSTANCE().getUserData();
        postData = UserRepository.getINSTANCE().getPostData();
    }

    public User getUserData() {
        return userData;
    }

    public PostData getPostData() {
        return postData;
    }
}
