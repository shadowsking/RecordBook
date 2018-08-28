package com.example.king.recordbook.api.model.Group;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by KING on 02.02.2018.
 */

public class GroupModel implements Serializable{
    @SerializedName("id_group")
    private int id_group;
    @SerializedName("name")
    private String name;
    @SerializedName("admission_year")
    private int admission_year;

    public GroupModel(int id_group, String name, int admission_year) {
        this.id_group = id_group;
        this.name = name;
        this.admission_year = admission_year;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdmission_year() {
        return admission_year;
    }

    public void setAdmission_year(int admission_year) {
        this.admission_year = admission_year;
    }
}
