package com.example.king.recordbook.api.model.Point;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KING on 13.02.2018.
 */

public class PointModel implements Serializable {

    private int id_sort;
    private String sort_name;
    private ArrayList<PointBean> point;

    public static PointModel objectFromData(String str) {

        return new Gson().fromJson(str, PointModel.class);
    }

    public static PointModel objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PointModel.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getId_sort() {
        return id_sort;
    }

    public void setId_sort(int id_sort) {
        this.id_sort = id_sort;
    }

    public String getSort_name() {
        return sort_name;
    }

    public void setSort_name(String sort_name) {
        this.sort_name = sort_name;
    }

    public ArrayList<PointBean> getPoint() {
        return point;
    }

    public void setPoint(ArrayList<PointBean> point) {
        this.point = point;
    }

    public static class PointBean {

        private int id_point;
        private int id_sort;
        private String name;
        private List<PointResBean> point_res;

        public static PointBean objectFromData(String str) {

            return new Gson().fromJson(str, PointBean.class);
        }

        public static PointBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), PointBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public int getId_point() {
            return id_point;
        }

        public void setId_point(int id_point) {
            this.id_point = id_point;
        }

        public int getId_sort() {
            return id_sort;
        }

        public void setId_sort(int id_sort) {
            this.id_sort = id_sort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<PointResBean> getPoint_res() {
            return point_res;
        }

        public void setPoint_res(List<PointResBean> point_res) {
            this.point_res = point_res;
        }

        public static class PointResBean {

            private int id_res;
            private int id_point;
            private String point;
            private String result;
            private String sex;

            public static PointResBean objectFromData(String str) {

                return new Gson().fromJson(str, PointResBean.class);
            }

            public static PointResBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), PointResBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public int getId_res() {
                return id_res;
            }

            public void setId_res(int id_res) {
                this.id_res = id_res;
            }

            public int getId_point() {
                return id_point;
            }

            public void setId_point(int id_point) {
                this.id_point = id_point;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }
    }
}
