package com.robot.tongbanjie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.entity.PrimeProduct;

import java.util.List;

import butterknife.ButterKnife;

public class PrimeProductAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static enum ITEM_TYPE {
        ITEM_TYPE_COMMON,
        ITEM_TYPE_SAFE_TIPS;
    }
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<PrimeProduct> mDatas;

    public PrimeProductAdapter1(Context context, List<PrimeProduct> datas) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_COMMON.ordinal()) {
            return new ProductViewHolder(mLayoutInflater.inflate(R.layout.list_item_prime_product, parent, false));
        } else {
            return new BottomHolder(mLayoutInflater.inflate(R.layout.list_item_prime_product_bottom, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductViewHolder) {
            ProductViewHolder productViewHolder = (ProductViewHolder) holder;
        } else if (holder instanceof BottomHolder) {
            BottomHolder bottomHolder = (BottomHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int count = getItemCount();
        if (position == count - 1) {
            return ITEM_TYPE.ITEM_TYPE_SAFE_TIPS.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.text_view)
//        TextView mTextView;
//        @Bind(R.id.image_view)
//        ImageView mImageView;

        ProductViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ImageViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }

    public static class BottomHolder extends RecyclerView.ViewHolder {
        BottomHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "安全保障第一！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
