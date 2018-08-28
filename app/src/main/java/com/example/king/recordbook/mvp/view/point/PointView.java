package com.example.king.recordbook.mvp.view.point;

import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.mvp.base.BaseView;

import java.util.ArrayList;

/**
 * Created by KING on 08.06.2018.
 */

public interface PointView extends BaseView{
    void setAdapter(ArrayList<PointModel.PointBean> data);
    void showGroupFragment(int id_point);
}
