package com.example.king.recordbook.mvp.view.student;

import com.example.king.recordbook.api.model.Result.ResultModel;
import com.example.king.recordbook.api.model.Student.StudentModel;
import com.example.king.recordbook.mvp.base.BaseView;

import java.util.ArrayList;

/**
 * Created by KING on 07.02.2018.
 */

public interface StudentView extends BaseView{
    void setAdapter(ArrayList<StudentModel> data);
    void showResultsFragment(int id_student, int semester);
}
