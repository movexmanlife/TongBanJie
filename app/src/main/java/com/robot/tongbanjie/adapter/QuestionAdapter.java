package com.robot.tongbanjie.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.DensityUtils;

/**
 *
 */
public class QuestionAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    public QuestionAdapter(Context context) {
        this.mContext = context;
    }

    // 设置组视图的显示文字
    private String[] summaryQuestion = new String[]{"建议配比中的1R、2R、3R分别代表什么含义？",
            "铜板能用来做什么？是否随时都可以使用？"};
    // 子视图显示文字
    private String[][] detailDesc = new String[][]{
            {"风险等级是根据产品投资方向、合作伙伴的资金实力、理财期限、该产品是否哦承诺回购、是否有担保、担保公司实力" +
            "资质等因素做出的等级区分。1R:超低风险，适合做配置安全垫 2R:较低风险，适合做部分资产配置 3R:有一定风险，请" +
            "根据个人实际情况做资产配置"},
            {"铜板可以兑换成话费或者集分宝哦，同事目前还支持在铜板商城兑换加息券和代金券。话费：2000铜板起兑换，换算公式：" +
            "1000铜板=10元话费，以此类推；集分宝：1铜板起兑换，换算公式：1000铜板=1000集分宝，以此类推，兑换前需绑定支付宝账号。" +
            "积分商城兑换可具体查看详细兑换规则。"}

    };

    // 定义一个获得文字信息的方法
    TextView getTextView(int textSize, int textColor) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        TextView textView = new TextView(
                mContext);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(DensityUtils.dip2px(mContext, 10), DensityUtils.dip2px(mContext, 15), DensityUtils.dip2px(mContext, 2),
                DensityUtils.dip2px(mContext, 15));
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        return textView;
    }

    // 定义一个获得文字信息的方法
    ImageView getImageView() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        ImageView imageView = new ImageView(
                mContext);
        imageView.setLayoutParams(lp);
        imageView.setPadding(0, 0, 0, 0);
        return imageView;
    }

    // 重写ExpandableListAdapter中的各个方法
    @Override
    public int getGroupCount() {
        return summaryQuestion.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return summaryQuestion[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return detailDesc[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return detailDesc[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder = null;
        if (convertView == null) {
            LinearLayout linearLayout = new LinearLayout(
                    mContext);
            linearLayout.setBackgroundResource(R.color.white);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            TextView textView = getTextView(16, mContext.getResources().getColor(R.color.black));
            linearLayout.addView(textView);

            ImageView arrow = getImageView();
            linearLayout.addView(arrow);

            convertView = linearLayout;
            viewHolder = new GroupViewHolder();
            viewHolder.textView = textView;
            viewHolder.imageView = arrow;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(getGroup(groupPosition).toString());
        if (isExpanded){
            viewHolder.imageView.setImageResource(R.drawable.arrow_down);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.arrow_left);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        if (convertView == null) {
            LinearLayout childLayout = new LinearLayout(
                    mContext);
            childLayout.setOrientation(LinearLayout.VERTICAL);
            childLayout.setBackgroundResource(R.color.expand_list_view_detail_bg);
            TextView textView = getTextView(16, mContext.getResources().getColor(R.color.black));
            childLayout.addView(textView);

            convertView = childLayout;
            viewHolder = new ChildViewHolder();
            viewHolder.textView = textView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(getChild(groupPosition, childPosition).toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition,
                                     int childPosition) {
        return true;
    }

    public static class GroupViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

    public static class ChildViewHolder {
        public TextView textView;
    }
}
