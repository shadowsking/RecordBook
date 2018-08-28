package com.example.king.recordbook.mvp.view.group;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.mvp.presenter.GroupPresenter;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KING on 04.02.2018.
 */

public class GroupRVAdapter extends RecyclerView.Adapter<GroupRVAdapter.ViewHolder>{
    private final ArrayList<GroupModel> mValues;
    private GroupPresenter mGroupPresenter;


    GroupRVAdapter(GroupPresenter mGroupPresenter) {
        this.mGroupPresenter = mGroupPresenter;
        mValues = new ArrayList<>();
    }

    public void addData(ArrayList<GroupModel> data){
        if(data!=null){
            mValues.clear();
            mValues.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.groupName.setText(mValues.get(position).getName());

        holder.cardView.setOnClickListener(v -> {
            mGroupPresenter.addGroup(mValues.get(position).getId_group(), mValues.get(position).getName(), mValues.get(position).getAdmission_year());
            mGroupPresenter.getView().showStudentFragment(mValues.get(position).getId_group(), mValues.get(position).getAdmission_year());
            //Log.d("DATE", "onBindViewHolder: " + Calendar.getInstance().get(Calendar.YEAR));
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.groupName)
        TextView groupName;

        @BindView(R.id.cardPanel)
        CardView cardView;

        private final View mView;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView = view;
        }
    }
}
