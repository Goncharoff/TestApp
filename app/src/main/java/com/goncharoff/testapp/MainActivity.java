package com.goncharoff.testapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel;

    private ImageView avatar;
    private TextView name;
    private TextView professionText;
    private TextView numberOfSubs;
    private TextView description;
    private TextView phoneNumber;
    private TextView email;
    private TextView subsNumber;
    private TextView rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProfileViews();
        initProfileViewsContent();
    }

    private ProfileViewModel getProfileViewModel() {
        if (profileViewModel == null) {
            profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        }
        return profileViewModel;
    }


    private void initProfileViews() {
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        professionText = findViewById(R.id.professionText);
        numberOfSubs = findViewById(R.id.numberOfSubs);
        description = findViewById(R.id.description);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.emailAddress);
        subsNumber = findViewById(R.id.subsNumber);
        rating = findViewById(R.id.rating);
    }

    private void initProfileViewsContent() {
        Glide.with(this).load(getProfileViewModel().getUserData().getPictureUrl()).into(avatar);
        name.setText(getString(R.string.name, getProfileViewModel().getUserData().getName(), getProfileViewModel().getUserData().getLastName()));
        professionText.setText(getProfileViewModel().getUserData().getTitle());
        numberOfSubs.setText(String.valueOf(getProfileViewModel().getUserData().getNumber()));
        description.setText(getProfileViewModel().getUserData().getDescription());
        phoneNumber.setText(getProfileViewModel().getUserData().getPhone());
        email.setText(getProfileViewModel().getUserData().getEmail());
        subsNumber.setText(getProfileViewModel().getUserData().getFollowers());
        rating.setText(String.valueOf(getProfileViewModel().getUserData().getRating()));
    }
}
