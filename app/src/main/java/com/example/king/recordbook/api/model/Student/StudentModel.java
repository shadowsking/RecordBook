package com.example.king.recordbook.api.model.Student;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by KING on 09.02.2018.
 */

public class StudentModel implements Serializable{
    @SerializedName("id_student")
    private int id_student;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("sex")
    private String sex;

    private double result;

    public StudentModel(int id_student, String full_name, String sex) {
        this.id_student = id_student;
        this.full_name = full_name;
        this.sex = sex;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getName() {
        return full_name;
    }

    public void setName(String name) {
        this.full_name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
