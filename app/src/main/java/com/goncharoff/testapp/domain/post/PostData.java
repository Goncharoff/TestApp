package com.goncharoff.testapp.domain.post;

import java.util.Set;

public class PostData {

    private long dateUpdated;
    private Set<Photo> photos;
    private Set<Post> posts;
    private Set<PostAction> postActions;
    private Set<PostQuote> postQuotes;
    private long showFromDate;

    public long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<PostAction> getPostActions() {
        return postActions;
    }

    public void setPostActions(Set<PostAction> postActions) {
        this.postActions = postActions;
    }

    public Set<PostQuote> getPostQuotes() {
        return postQuotes;
    }

    public void setPostQuotes(Set<PostQuote> postQuotes) {
        this.postQuotes = postQuotes;
    }

    public long getShowFromDate() {
        return showFromDate;
    }

    public void setShowFromDate(long showFromDate) {
        this.showFromDate = showFromDate;
    }
}
