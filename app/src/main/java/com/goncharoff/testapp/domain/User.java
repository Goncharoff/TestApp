package com.goncharoff.testapp.domain;

public class User {

    private long id;
    private long number;
    private String name;
    private String lastName;
    private String title;
    private String pictureUrl;
    private String description;
    private String phone;
    private String email;
    private String followers;
    private long rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", followers='" + followers + '\'' +
                ", rating=" + rating +
                '}';
    }
}
