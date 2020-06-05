package com.goncharoff.testapp.view;

import androidx.lifecycle.ViewModel;

import com.goncharoff.testapp.domain.Post;
import com.goncharoff.testapp.domain.json_objects.User;
import com.goncharoff.testapp.domain.json_objects.post.PostData;
import com.goncharoff.testapp.repository.UserRepository;

import java.util.List;

public class ProfileViewModel extends ViewModel {

    private User userData;
    private PostData postData;
    private List<Post> posts;

    public ProfileViewModel() {
        userData = UserRepository.getINSTANCE().getUserData();
        postData = UserRepository.getINSTANCE().getPostData();
        posts = UserRepository.getINSTANCE().getFilteredAndOrderedPosts();
    }

    public User getUserData() {
        return userData;
    }

    public PostData getPostData() {
        return postData;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
