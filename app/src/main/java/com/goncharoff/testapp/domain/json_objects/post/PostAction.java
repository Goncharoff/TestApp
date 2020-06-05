package com.goncharoff.testapp.domain.json_objects.post;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PostAction implements Dateable {

    private long id;
    private String title;
    private String target;

    @SerializedName("btn_name")
    private String buttonName;

    @SerializedName("action_type")
    private ActionType actionType;

    @SerializedName("date_created")
    private long dateCreated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

        PostAction that = (PostAction) o;

        if (id != that.id) return false;
        if (dateCreated != that.dateCreated) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(buttonName, that.buttonName))
            return false;
        if (actionType != that.actionType) return false;
        return Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (buttonName != null ? buttonName.hashCode() : 0);
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (int) (dateCreated ^ (dateCreated >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PostAction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", target='" + target + '\'' +
                ", buttonName='" + buttonName + '\'' +
                ", actionType=" + actionType +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
