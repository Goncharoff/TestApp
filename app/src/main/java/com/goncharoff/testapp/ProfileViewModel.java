package com.goncharoff.testapp;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.goncharoff.testapp.domain.User;
import com.goncharoff.testapp.repository.UserRepository;

public class ProfileViewModel extends ViewModel {

    private User userData;

    public ProfileViewModel() {
        userData = UserRepository.getINSTANCE().getUserData();
    }

    public User getUserData() {
        return userData;
    }
}
