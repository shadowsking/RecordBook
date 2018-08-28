package com.example.king.recordbook.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by KING on 02.02.2018.
 */

public class Token implements Serializable{
    @SerializedName("token")
    private String token;
    @SerializedName("valid")
    private boolean valid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
