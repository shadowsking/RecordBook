package com.example.king.recordbook.api.model.Result;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by KING on 10.06.2018.
 */

public class ResultModel {
    private int id_result;
    private int id_student;
    private int id_point;
    private double result;
    private int point;
    private int semester;
    private String name;

    public static ResultModel objectFromData(String str) {

        return new Gson().fromJson(str, ResultModel.class);
    }

    public static ResultModel objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ResultModel.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getId_result() {
        return id_result;
    }

    public void setId_result(int id_result) {
        this.id_result = id_result;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public int getId_point() {
        return id_point;
    }

    public void setId_point(int id_point) {
        this.id_point = id_point;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }
}
