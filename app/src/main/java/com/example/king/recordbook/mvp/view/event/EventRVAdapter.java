package com.example.king.recordbook.mvp.view.event;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.recordbook.R;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.mvp.presenter.EventPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KING on 03.06.2018.
 */

public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.ViewHolder>{
    private final ArrayList<PointModel> mValues;
    private EventPresenter mEventPresenter;

    EventRVAdapter(EventPresenter mEventPresenter) {
        this.mEventPresenter = mEventPresenter;
        mValues = new ArrayList<>();
    }
    public void addData(ArrayList<PointModel> data){
        if(data!=null){
            mValues.clear();
            mValues.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public EventRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item, parent, false);
        return new EventRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventRVAdapter.ViewHolder holder, int position) {
        holder.pointName.setText(mValues.get(position).getSort_name());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mEventPresenter.getView().showPointFragment(mValues.get(position).getId_sort());
                mEventPresenter.getView().showPointFragment(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.eventName)
        TextView pointName;

        @BindView(R.id.cardEventPanel)
        CardView cardView;

        private final View mView;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView = view;
        }
    }
}
