package com.example.king.recordbook.mvp.view.result;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Result.ResultModel;
import com.example.king.recordbook.mvp.presenter.ResultsPresenter;
import com.example.king.recordbook.mvp.view.student.StudentRVAdapter;
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KING on 09.06.2018.
 */

public class ResultRVAdapter extends RecyclerView.Adapter<ResultRVAdapter.ViewHolder>{
    private ArrayList<ResultModel> mValues;
    private ResultsPresenter mResultPresenter;


    ResultRVAdapter(ResultsPresenter mResultPresenter) {
        this.mResultPresenter = mResultPresenter;
        mValues = new ArrayList<>();
    }

    public void addData(ArrayList<ResultModel> data){
        if(data!=null){
            mValues.clear();
            mValues.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ResultRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_list_item, parent, false);
        return new ResultRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultRVAdapter.ViewHolder holder, final int position) {
        holder.result.setText(mValues.get(position).getName() + " - " + mValues.get(position).getResult());
        holder.cardView.setOnClickListener(v -> {
            //holder.graphView.setVisibility();
            mResultPresenter.getStudentPResults(mValues.get(position).getId_student(), mValues.get(position).getId_point());
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.result)
        TextView result;

        @BindView(R.id.cardResultPanel)
        CardView cardView;

        private final View mView;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView = view;
        }
    }
}
