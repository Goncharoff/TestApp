package com.goncharoff.testapp.domain.post;

public class Photo {

    private String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        return photoUrl != null ? photoUrl.equals(photo.photoUrl) : photo.photoUrl == null;
    }

    @Override
    public int hashCode() {
        return photoUrl != null ? photoUrl.hashCode() : 0;
    }
}
