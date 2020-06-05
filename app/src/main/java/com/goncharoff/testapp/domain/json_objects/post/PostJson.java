package com.goncharoff.testapp.domain.json_objects.post;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PostJson implements Dateable {

    private long id;
    private String text;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("date_created")
    private long dateCreated;

    public PostJson(long id, String imageUrl, String text, long dateCreated) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.text = text;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostJson post = (PostJson) o;

        if (id != post.id) return false;
        if (dateCreated != post.dateCreated) return false;
        if (!Objects.equals(imageUrl, post.imageUrl))
            return false;
        return Objects.equals(text, post.text);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (int) (dateCreated ^ (dateCreated >>> 32));
        return result;
    }


}
