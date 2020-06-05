package com.goncharoff.testapp;

import android.os.Bundle;
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
        UserRepository.getINSTANCE().getFilteredAndOrderedPosts();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        photosRecyclerView.setLayoutManager(layoutManager);
        photosRecyclerView.setAdapter(new PhotosAdapter(this, getProfileViewModel().getPostData().getPhotos()));

        LinearLayoutManager postsLayoutManager = new LinearLayoutManager(this);
        postsRecyclerView.setLayoutManager(postsLayoutManager);
        postsRecyclerView.setAdapter(new PostAdapter(this, UserRepository.getINSTANCE().getFilteredAndOrderedPosts()));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.posts_devider)));
        postsRecyclerView.addItemDecoration(itemDecorator);
    }
}
