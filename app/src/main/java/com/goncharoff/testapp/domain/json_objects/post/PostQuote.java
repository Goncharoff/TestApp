package com.goncharoff.testapp.domain.json_objects.post;

import com.google.gson.annotations.SerializedName;

public class PostQuote {
    private long id;
    @SerializedName("quote_1")
    private String firstQuote;
    @SerializedName("quote_2")
    private String secondQuote;
    @SerializedName("date_created")
    private long dateCreated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstQuote() {
        return firstQuote;
    }

    public void setFirstQuote(String firstQuote) {
        this.firstQuote = firstQuote;
    }

    public String getSecondQuote() {
        return secondQuote;
    }

    public void setSecondQuote(String secondQuote) {
        this.secondQuote = secondQuote;
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

        PostQuote postQuote = (PostQuote) o;

        if (id != postQuote.id) return false;
        if (dateCreated != postQuote.dateCreated) return false;
        if (firstQuote != null ? !firstQuote.equals(postQuote.firstQuote) : postQuote.firstQuote != null)
            return false;
        return secondQuote != null ? secondQuote.equals(postQuote.secondQuote) : postQuote.secondQuote == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstQuote != null ? firstQuote.hashCode() : 0);
        result = 31 * result + (secondQuote != null ? secondQuote.hashCode() : 0);
        result = 31 * result + (int) (dateCreated ^ (dateCreated >>> 32));
        return result;
    }
}
