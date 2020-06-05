package com.goncharoff.testapp.domain;

import com.goncharoff.testapp.domain.json_objects.post.Dateable;
import com.goncharoff.testapp.domain.json_objects.post.PostAction;
import com.goncharoff.testapp.domain.json_objects.post.PostData;
import com.goncharoff.testapp.domain.json_objects.post.PostJson;
import com.goncharoff.testapp.domain.json_objects.post.PostQuote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    //Factory method for creating Posts from PostData
    public static List<Post> fromData(PostData postData, long startDate) {

        List<Post> resultPosts = new ArrayList<>();

        List<PostJson> postJsons = postData.getPosts();
        List<PostQuote> postQuotes = postData.getPostQuotes();
        List<PostAction> postActions = postData.getPostActions();

        filterByStartDate(resultPosts, postJsons, startDate, PostType.POST);
        filterByStartDate(resultPosts, postQuotes, startDate, PostType.QUOTE);
        filterByStartDate(resultPosts, postActions, startDate, PostType.ACTION);

        return resultPosts;
    }

    //Filtering collection element by started date, if started date more then created date of item, doesn't add it to result collection
    private static <T extends Dateable> void filterByStartDate(List<Post> collectionToAdd, List<T> inputCollection, long startDate, PostType postType) {
        for (int i = 0; i < inputCollection.size(); i++) {
            if (inputCollection.get(i).getDateCreated() >= startDate)
                collectionToAdd.add(new Post(inputCollection.get(i).getId(), inputCollection.get(i).getDateCreated(), postType));
        }
    }

    //Compare in reverse order by date
    @Override
    public int compareTo(Post o) {
        return -this.dateCreated.compareTo(o.getDateCreated());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        return Objects.equals(dateCreated, post.dateCreated);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
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
