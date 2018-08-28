package com.example.king.recordbook.mvp.view.result;

import com.example.king.recordbook.api.model.Result.ResultModel;
import com.example.king.recordbook.mvp.base.BaseView;

import java.util.ArrayList;

/**
 * Created by KING on 09.06.2018.
 */

public interface ResultView extends BaseView {
    void setAdapter(ArrayList<ResultModel> data);
    void initGraph(ArrayList<ResultModel> response);
    void setTotalPoints(int points);
}
