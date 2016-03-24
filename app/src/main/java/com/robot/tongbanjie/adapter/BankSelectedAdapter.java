package com.robot.tongbanjie.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.entity.BankInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BankSelectedAdapter extends BaseAdapter{
    private String iconBaseUrl = "https://apimg.alipay.com/combo.png?d=cashier&t=";
    private Context mContext;
    private List<BankInfo> bankInfoList;
    private String selecedBankName;

    public BankSelectedAdapter(Context context, List<BankInfo> bankInfoList) {
        this.mContext = context;
        this.bankInfoList = bankInfoList;
    }

    @Override
    public int getCount() {
        return bankInfoList == null ? 0 : bankInfoList.size();
    }


    @Override
    public Object getItem(int position) {
        return bankInfoList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_bank_selected, null);
            viewHolder = new ViewHolder();
            viewHolder.content = (LinearLayout) convertView.findViewById(R.id.content);
            viewHolder.bankIcon = (ImageView) convertView.findViewById(R.id.bank_icon);
            viewHolder.bankName = (TextView) convertView.findViewById(R.id.bank_name);
            viewHolder.isSelectedImg = (ImageView) convertView.findViewById(R.id.selected_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        BankInfo bankInfo = (BankInfo) getItem(position);
        Picasso.with(mContext).load(iconBaseUrl + bankInfo.code).into(viewHolder.bankIcon);
        viewHolder.bankName.setText(bankInfo.name);

        if (TextUtils.equals(selecedBankName, bankInfo.name) ) {
            viewHolder.content.setBackgroundResource(R.drawable.bank_seleced);
            viewHolder.isSelectedImg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.content.setBackgroundResource(R.color.white);
            viewHolder.isSelectedImg.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void setSelectedBankName(String selectedBankName) {
        this.selecedBankName = selectedBankName;
    }

    public String getSelectedBankName() {
        return this.selecedBankName;
    }

    public static class ViewHolder {
        private LinearLayout content;
        private ImageView bankIcon;
        private TextView bankName;
        private ImageView isSelectedImg;
    }
}
