package com.goncharoff.testapp.domain.json_objects.post;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostData postData = (PostData) o;

        if (dateUpdated != postData.dateUpdated) return false;
        if (showFromDate != postData.showFromDate) return false;
        if (!Objects.equals(photos, postData.photos))
            return false;
        if (!Objects.equals(posts, postData.posts)) return false;
        if (!Objects.equals(postActions, postData.postActions))
            return false;
        return postQuotes != null ? postQuotes.equals(postData.postQuotes) : postData.postQuotes == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (dateUpdated ^ (dateUpdated >>> 32));
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        result = 31 * result + (posts != null ? posts.hashCode() : 0);
        result = 31 * result + (postActions != null ? postActions.hashCode() : 0);
        result = 31 * result + (postQuotes != null ? postQuotes.hashCode() : 0);
        result = 31 * result + (int) (showFromDate ^ (showFromDate >>> 32));
        return result;
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
