package com.goncharoff.testapp.domain;

import com.goncharoff.testapp.domain.json_objects.post.PostAction;
import com.goncharoff.testapp.domain.json_objects.post.PostData;
import com.goncharoff.testapp.domain.json_objects.post.PostJson;
import com.goncharoff.testapp.domain.json_objects.post.PostQuote;

import java.util.ArrayList;
import java.util.List;

public class Post implements Comparable<Post> {

    private long id;
    private Long dateCreated;
    private PostType postType;

    private Post(long id, long dateCreated, PostType postType) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.postType = postType;
    }

    public Post() {
    }

    //TODO remove repeated code
    public static List<Post> fromData(PostData postData, long startDate) {
        List<Post> posts = new ArrayList<>();
        List<PostJson> postJsons = postData.getPosts();
        List<PostQuote> postQuotes = postData.getPostQuotes();
        List<PostAction> postActions = postData.getPostActions();

        for (int i = 0; i < postJsons.size(); i++) {
            if (postJsons.get(i).getDateCreated() >= startDate)
                posts.add(new Post(postJsons.get(i).getId(), postJsons.get(i).getDateCreated(), PostType.POST));
        }

        for (int i = 0; i < postQuotes.size(); i++) {
            if (postQuotes.get(i).getDateCreated() >= startDate)
                posts.add(new Post(postQuotes.get(i).getId(), postQuotes.get(i).getDateCreated(), PostType.QUOTE));
        }

        for (int i = 0; i < postActions.size(); i++) {
            if (postActions.get(i).getDateCreated() >= startDate)
                posts.add(new Post(postActions.get(i).getId(), postActions.get(i).getDateCreated(), PostType.ACTION));
        }

        return posts;
    }


    @Override
    public int compareTo(Post o) {
        return this.dateCreated.compareTo(o.getDateCreated());
    }

    public long getId() {
        return id;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public PostType getPostType() {
        return postType;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", postType=" + postType +
                '}';
    }
}
