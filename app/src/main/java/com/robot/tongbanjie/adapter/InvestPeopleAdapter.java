package com.robot.tongbanjie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.entity.InvestPeople;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InvestPeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int ITEM_TYPE_COMMON = 0;
    public static final int ITEM_TYPE_NETWORK_ERROR = 1;
    private boolean isShowNetworkErrorTips = false;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<InvestPeople> mDatas;

    public InvestPeopleAdapter(Context context, List<InvestPeople> datas) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_COMMON) {
            return new InvestPeopleViewHolder(mLayoutInflater.inflate(R.layout.list_item_invest_people, parent, false));
        } else {
            return new NetworkHolder(mLayoutInflater.inflate(R.layout.view_network_unusual, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof InvestPeopleViewHolder) {
            InvestPeopleViewHolder peopleViewHolder = (InvestPeopleViewHolder) holder;
            InvestPeople investPeople = mDatas.get(position);

            peopleViewHolder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            peopleViewHolder.date.setText(investPeople.date);
            peopleViewHolder.sum.setText(getFormatSum(investPeople.sum));
            peopleViewHolder.phone.setText(investPeople.phone);
        } else {
            NetworkHolder networkHolder = (NetworkHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        InvestPeople InvestPeople = mDatas.get(position);
        if (InvestPeople.viewType == ITEM_TYPE_NETWORK_ERROR) {
            return ITEM_TYPE_NETWORK_ERROR;
        } else {
            return ITEM_TYPE_COMMON;
        }
    }

    private String getFormatSum(float sum) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(sum);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class InvestPeopleViewHolder extends RecyclerView.ViewHolder {
        View root;
        @Bind(R.id.phone)
        TextView phone;
        @Bind(R.id.sum)
        TextView sum;
        @Bind(R.id.date)
        TextView date;
        
        InvestPeopleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.root = view;
        }
    }

    public static class NetworkHolder extends RecyclerView.ViewHolder {
        View root;
        NetworkHolder(View view) {
            super(view);
            this.root = view;
        }
    }

    public void isShowNetworkErrorTips(boolean isShow) {
        isShowNetworkErrorTips = isShow;
        if (isShow) {
            if (mDatas.get(0).viewType != ITEM_TYPE_NETWORK_ERROR) {
                InvestPeople InvestPeople = new InvestPeople();
                InvestPeople.viewType = ITEM_TYPE_NETWORK_ERROR;
                mDatas.add(0, InvestPeople);
            }
        } else {
            if (mDatas.get(0).viewType == ITEM_TYPE_NETWORK_ERROR) { // 第一个是网络错误
                mDatas.remove(0);
            }
        }
        notifyDataSetChanged();
    }
}
