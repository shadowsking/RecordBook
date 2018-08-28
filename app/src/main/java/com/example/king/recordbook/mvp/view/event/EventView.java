package com.example.king.recordbook.mvp.view.event;

import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.mvp.base.BaseView;

import java.util.ArrayList;

/**
 * Created by KING on 03.06.2018.
 */

public interface EventView extends BaseView{
    void setAdapter(ArrayList<PointModel> data);
    void showPointFragment(int id_event);
}
