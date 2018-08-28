package com.example.king.recordbook.mvp.view.student;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Student.StudentModel;
import com.example.king.recordbook.mvp.presenter.StudentPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KING on 09.02.2018.
 */

public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.ViewHolder>{
    private ArrayList<StudentModel> mValues;
    private StudentPresenter mStudentPresenter;


    StudentRVAdapter(StudentPresenter mStudentPresenter) {
        this.mStudentPresenter = mStudentPresenter;
        mValues = new ArrayList<>();
    }

    public void addData(ArrayList<StudentModel> data){
        if(data!=null){
            mValues.clear();
            mValues.addAll(data);
            notifyDataSetChanged();
            Log.d("LOL", String.valueOf(data.size()));
        }
    }

    @Override
    public StudentRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);
        return new StudentRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentRVAdapter.ViewHolder holder, final int position) {
        holder.studName.setText(mValues.get(position).getName());
        double result = mValues.get(position).getResult();
        if(result!=0) holder.studPoint.setText(String.valueOf(mValues.get(position).getResult()));
        holder.cardView.setOnClickListener(v -> {
            if(mStudentPresenter.getId_point()!=-1) {
                if (result != 0)
                    mStudentPresenter.changeResult();
                else
                    mStudentPresenter.addResult(mValues.get(position).getId_student(), mStudentPresenter.getSemester());
            } else mStudentPresenter.getView().showResultsFragment(mValues.get(position).getId_student(), mStudentPresenter.getSemester());
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.studentName)
        TextView studName;

        @BindView(R.id.studentPoint)
        TextView studPoint;

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
