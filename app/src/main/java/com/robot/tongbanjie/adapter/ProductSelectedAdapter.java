
package com.robot.tongbanjie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.widget.SingleChoiceListItemView;


public class ProductSelectedAdapter extends BaseAdapter {

    private static String[] names = new String[] {"新手专享", "预约专区", "短期理财", "定期理财", "等额本息", "转让专区"};
    private LayoutInflater inflater;
    private Context mContext;

    public ProductSelectedAdapter(Context context)
    {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return names.length;
    }

    @Override
    public Object getItem(int position)
    {
        return names[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = new SingleChoiceListItemView(mContext, null);
            viewHolder = new ViewHolder();
            viewHolder.name = ((SingleChoiceListItemView)convertView).getNameView();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.name.setText(names[position]);
        return convertView;
    }

    public static class ViewHolder {
        public TextView name;
    }
}
