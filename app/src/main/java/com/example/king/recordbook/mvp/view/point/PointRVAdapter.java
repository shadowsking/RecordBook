package com.example.king.recordbook.mvp.view.point;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.mvp.presenter.PointPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KING on 08.06.2018.
 */

public class PointRVAdapter extends RecyclerView.Adapter<PointRVAdapter.ViewHolder>{
    private ArrayList<PointModel.PointBean> mValues;

    private PointPresenter mPointPresenter;
    private int id_event;

    PointRVAdapter(PointPresenter mPointPresenter) {
        this.mPointPresenter = mPointPresenter;
        mValues = new ArrayList<>();
    }

    void addData(@NonNull ArrayList<PointModel.PointBean> data, int id_event){
        this.id_event = id_event;
        mValues.clear();
        mValues.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public PointRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);
        return new PointRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PointRVAdapter.ViewHolder holder, final int position) {
        holder.studName.setText(mValues.get(position).getName());

        holder.cardView.setOnClickListener(v -> mPointPresenter.getView()
                .showGroupFragment(mValues.get(position).getId_point()));

    }

    @Override
    public int getItemCount() {
        return  mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.studentName)
        TextView studName;

        @BindView(R.id.cardStudentPanel)
        CardView cardView;

        private final View mView;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView = view;
        }
    }
}
