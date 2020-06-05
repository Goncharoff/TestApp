package com.goncharoff.testapp.domain.json_objects.post;

import com.google.gson.annotations.SerializedName;

public enum ActionType {
    @SerializedName("email")
    EMAIL,
    @SerializedName("phone")
    CALL
}
