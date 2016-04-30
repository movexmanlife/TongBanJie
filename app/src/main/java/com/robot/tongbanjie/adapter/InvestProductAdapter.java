package com.robot.tongbanjie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.NewerActivity;
import com.robot.tongbanjie.entity.InvestProduct;
import com.robot.tongbanjie.entity.PrimeProduct;
import com.robot.tongbanjie.util.NetworkUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InvestProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int ITEM_TYPE_COMMON = 0;
    public static final int ITEM_TYPE_TONG_BAO = 1;
    public static final int ITEM_TYPE_NETWORK_ERROR = 2;
    private boolean isShowNetworkErrorTips = false;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<InvestProduct> mDatas;

    public InvestProductAdapter(Context context, List<InvestProduct> datas) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_COMMON) {
            return new ProductViewHolder(mLayoutInflater.inflate(R.layout.list_item_invest, parent, false));
        } else if (viewType == ITEM_TYPE_TONG_BAO){
            return new HeaderHolder(mLayoutInflater.inflate(R.layout.list_item_invest_header, parent, false));
        } else {
            return new NetworkHolder(mLayoutInflater.inflate(R.layout.view_network_unusual, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ProductViewHolder) {
            ProductViewHolder productViewHolder = (ProductViewHolder) holder;
            InvestProduct investProduct = mDatas.get(position);
            productViewHolder.icon.setImageResource(investProduct.resId);
            productViewHolder.title.setText(investProduct.title);
            productViewHolder.desc.setText(investProduct.desc);

            if (investProduct.isReservation) {
                productViewHolder.reservation_layout.setVisibility(View.VISIBLE);
                productViewHolder.reservation.setText(investProduct.reservation);
            } else {
                productViewHolder.reservation_layout.setVisibility(View.GONE);
            }

            if (investProduct.hasTimeLimit) {
                productViewHolder.limit.setText(String.valueOf(investProduct.timeLimit));
                productViewHolder.limit.setVisibility(View.VISIBLE);
            } else {
                productViewHolder.limit.setVisibility(View.GONE);
            }

            productViewHolder.icon.setImageResource(investProduct.resId);
            productViewHolder.icon.setImageResource(investProduct.resId);

            productViewHolder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        } else if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
        } else if (holder instanceof NetworkHolder) {
            NetworkHolder networkHolder = (NetworkHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        InvestProduct investProduct = mDatas.get(position);
        if (investProduct.viewType == ITEM_TYPE_NETWORK_ERROR) {
            return ITEM_TYPE_NETWORK_ERROR;
        } else if (investProduct.viewType == ITEM_TYPE_TONG_BAO){
            return ITEM_TYPE_TONG_BAO;
        } else {
            return ITEM_TYPE_COMMON;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        View root;
        @Bind(R.id.icon)
        ImageView icon;
        @Bind(R.id.limit)
        TextView limit;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.desc)
        TextView desc;
        @Bind(R.id.reservation_layout)
        LinearLayout reservation_layout;
        @Bind(R.id.reservation)
        TextView reservation;

        ProductViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.root = view;
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        View root;
        HeaderHolder(View view) {
            super(view);
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
                InvestProduct investProduct = new InvestProduct();
                investProduct.viewType = ITEM_TYPE_NETWORK_ERROR;
                mDatas.add(0, investProduct);
            }
        } else {
            if (mDatas.get(0).viewType == ITEM_TYPE_NETWORK_ERROR) { // 第一个是网络错误
                mDatas.remove(0);
            }
        }
        notifyDataSetChanged();
    }
}
