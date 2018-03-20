package com.example.fluper.myapp.appUtil;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fluper on 12/3/18.
 */

public class CreateUserResponse {

    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;
}

