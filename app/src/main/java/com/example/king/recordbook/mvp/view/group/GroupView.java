package com.example.king.recordbook.mvp.view.group;

import android.support.v4.app.FragmentActivity;

import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.mvp.base.BaseView;

import java.util.ArrayList;

/**
 * Created by KING on 04.02.2018.
 */

public interface GroupView extends BaseView {
    void addArray(ArrayList<GroupModel> data);
    void showStudentFragment(int id_group, int admission_year);
    void addNewGroup();
}