package com.goncharoff.testapp.domain.post;

public class Post {

    private long id;
    private String imageUrl;
    private String text;
    private long dateCreated;

    public Post(long id, String imageUrl, String text, long dateCreated) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.text = text;
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (dateCreated != post.dateCreated) return false;
        if (imageUrl != null ? !imageUrl.equals(post.imageUrl) : post.imageUrl != null)
            return false;
        return text != null ? text.equals(post.text) : post.text == null;
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
