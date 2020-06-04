package com.goncharoff.testapp.domain.json_objects.post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostData {

    private long dateUpdated;
    private List<String> photos;
    private List<PostJson> posts;
    @SerializedName("posts_actions")
    private List<PostAction> postActions;
    @SerializedName("posts_quotes")
    private List<PostQuote> postQuotes;
    @SerializedName("show_from_date")
    private long showFromDate;

    public long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<PostJson> getPosts() {
        return posts;
    }

    public void setPosts(List<PostJson> posts) {
        this.posts = posts;
    }

    public List<PostAction> getPostActions() {
        return postActions;
    }

    public void setPostActions(List<PostAction> postActions) {
        this.postActions = postActions;
    }

    public List<PostQuote> getPostQuotes() {
        return postQuotes;
    }

    public void setPostQuotes(List<PostQuote> postQuotes) {
        this.postQuotes = postQuotes;
    }

    public long getShowFromDate() {
        return showFromDate;
    }

    public void setShowFromDate(long showFromDate) {
        this.showFromDate = showFromDate;
    }

    @Override
    public String toString() {
        return "PostData{" +
                "dateUpdated=" + dateUpdated +
                ", photos=" + photos +
                ", posts=" + posts +
                ", postActions=" + postActions +
                ", postQuotes=" + postQuotes +
                ", showFromDate=" + showFromDate +
                '}';
    }
}
