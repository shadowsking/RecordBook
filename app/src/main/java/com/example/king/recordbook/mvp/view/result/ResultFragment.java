package com.example.king.recordbook.mvp.view.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Result.ResultModel;
import com.example.king.recordbook.mvp.presenter.ResultsPresenter;
import com.example.king.recordbook.navigation.NavigationBuilder;
import com.example.king.recordbook.navigation.NavigationFragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.king.recordbook.navigation.AutoLayoutNavigationBuilder.navigation;

/**
 * Created by KING on 09.06.2018.
 */

public class ResultFragment extends NavigationFragment implements ResultView {
    @BindView(R.id.resultRView)
    RecyclerView recyclerView;
    @BindView(R.id.graph)
    GraphView graphView;
    @BindView(R.id.total_points)
    TextView total_points;

    private ResultsPresenter mResultsPresenter;
    public ResultRVAdapter adapter;

    public ResultFragment() {
    }

    public static Fragment newInstance(int id_student, int semester) {
        Bundle args = new Bundle();
        Fragment fragment = new ResultFragment();
        args.putInt("id_student", id_student);
        args.putInt("semester", semester);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavigationBuilder buildNavigation() {
        return navigation(R.layout.fragment_result)
                .includeToolbar()
                .toolbarTitle("Результаты");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container,savedInstanceState);
        ButterKnife.bind(this, view);

        int id_student = getArguments().getInt("id_student");
        int semester = getArguments().getInt("semester");

        mResultsPresenter = new ResultsPresenter();
        mResultsPresenter.attachView(this);
        mResultsPresenter.attachContext(this.getContext());

        adapter = new ResultRVAdapter(mResultsPresenter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mResultsPresenter.getResults(id_student, semester);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setAdapter(ArrayList<ResultModel> data) {
        adapter.addData(data);

        int summ = 0;
        for(int i=0; i<data.size(); i++) summ = summ + data.get(i).getPoint();
        setTotalPoints(summ);
    }


    @Override
    public void initGraph(ArrayList<ResultModel> response) {

        DataPoint[] dataPoints = new DataPoint[response.size()];

        for(int i=0; i<response.size(); i++){
            dataPoints[i] = new DataPoint(response.get(i).getSemester(), response.get(i).getResult());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graphView.removeAllSeries();
        graphView.addSeries(series);
    }

    @Override
    public void setTotalPoints(int points) {
        total_points.setText("Сумма: "+points);
    }

}
