package com.goncharoff.testapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.goncharoff.testapp.R;
import com.goncharoff.testapp.adapter.PhotosAdapter;
import com.goncharoff.testapp.adapter.PostAdapter;
import com.goncharoff.testapp.repository.UserRepository;

import java.util.Objects;

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

    private RecyclerView photosRecyclerView;

    private RecyclerView postsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initViewsContent();
    }

    //lazy init view model
    private ProfileViewModel getProfileViewModel() {
        if (profileViewModel == null) {
            profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        }
        return profileViewModel;
    }


    private void initViews() {
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        professionText = findViewById(R.id.professionText);
        numberOfSubs = findViewById(R.id.numberOfSubs);
        description = findViewById(R.id.description);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.emailAddress);
        subsNumber = findViewById(R.id.subsNumber);
        rating = findViewById(R.id.rating);

        photosRecyclerView = findViewById(R.id.photosGridView);

        postsRecyclerView = findViewById(R.id.posts);

    }

    private void initViewsContent() {
        setUpProfileViews();
        setUpPhoneNumberClick();
        setUpPhotosRecycler();
        setUpPostsRecycler();
    }

    private void setUpProfileViews() {
        //meh
        Glide.with(this).load(getProfileViewModel().getUserData().getPictureUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);

        name.setText(getString(R.string.name, getProfileViewModel().getUserData().getName(), getProfileViewModel().getUserData().getLastName()));
        professionText.setText(getProfileViewModel().getUserData().getTitle());
        numberOfSubs.setText(String.valueOf(getProfileViewModel().getUserData().getNumber()));
        description.setText(getProfileViewModel().getUserData().getDescription());
        phoneNumber.setText(getProfileViewModel().getUserData().getPhone());
        email.setText(getProfileViewModel().getUserData().getEmail());
        subsNumber.setText(getProfileViewModel().getUserData().getFollowers());
        rating.setText(String.valueOf(getProfileViewModel().getUserData().getRating()));
    }

    private void setUpPhoneNumberClick() {
        phoneNumber.setAutoLinkMask(Linkify.PHONE_NUMBERS);
        phoneNumber.setOnClickListener(it -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber.getText().toString(), null));
            this.startActivity(Intent.createChooser(intent, this.getString(R.string.app_to_call)));
        });
    }

    private void setUpPhotosRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        photosRecyclerView.setLayoutManager(layoutManager);
        photosRecyclerView.setAdapter(new PhotosAdapter(this, getProfileViewModel().getPostData().getPhotos()));
    }

    private void setUpPostsRecycler() {

        LinearLayoutManager postsLayoutManager = new LinearLayoutManager(this);
        postsRecyclerView.setLayoutManager(postsLayoutManager);
        postsRecyclerView.setAdapter(new PostAdapter(this, UserRepository.getINSTANCE().getFilteredAndOrderedPosts()));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.posts_devider)));
        postsRecyclerView.addItemDecoration(itemDecorator);
    }
}
